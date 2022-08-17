package com.yummythings.getto.common.interceptor;

import com.yummythings.getto.common.annotation.AuthCheck;
import com.yummythings.getto.common.component.TokenProvider;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
@RequiredArgsConstructor
public class AutCheckInterceptor implements HandlerInterceptor {

    private final TokenProvider tokenProvider;
    private static final String HEADER_TOKEN_KEY = "Bearer ";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 컨트롤러에 @AuthCheck 어노테이션이 사용되었는지 체크함
        AuthCheck authCheckAnnotation = ((HandlerMethod) handler).getMethodAnnotation(AuthCheck.class);
        if(authCheckAnnotation != null) {
            // @AuthCheck 가 포함되어있으면 인증 체크
            try {
                String token = resolveToken(request);
                tokenProvider.validateToken(token);
                saveAuthentication(token);
//                request.setAttribute("authMember", tokenProvider.getSubject(token));
            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
            }
        }
        return true;
    }

    private String resolveToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if(token == null) {
            throw  new JwtException("토큰이 없습니다.");
        }
        return token.replace(HEADER_TOKEN_KEY, "");
    }

    private void saveAuthentication(String jwt) {
        Authentication authentication = tokenProvider.getAuthentication(jwt);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.debug("Security Context에 '{}' 인증 정보를 저장했습니다, uri: {}", authentication.getName());
    }
}
