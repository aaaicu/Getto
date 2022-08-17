package com.yummythings.getto.common.config;

import com.yummythings.getto.common.annotation.AuthMember;
import com.yummythings.getto.common.interceptor.AutCheckInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class CustomWebMvcConfigurer implements WebMvcConfigurer {
    private final AutCheckInterceptor checkInterceptor;

    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(checkInterceptor).addPathPatterns("/**");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(loginUserIdResolver());
    }

    private HandlerMethodArgumentResolver loginUserIdResolver() {
        return new HandlerMethodArgumentResolver() {
            @Override
            public boolean supportsParameter(MethodParameter parameter) {
                return parameter.hasParameterAnnotation(AuthMember.class);
            }

            @Override
            public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory)  {
                Object resolved = null;
                if (String.class.isAssignableFrom(parameter.getParameterType())) {
                    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                    resolved = "anonymousUser".equals(principal) ? "" :((User)principal).getUsername();
                }
                return resolved;
            }
        };

    }
}
