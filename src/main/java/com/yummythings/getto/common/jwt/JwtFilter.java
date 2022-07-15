package com.yummythings.getto.common.jwt;

import com.yummythings.getto.common.component.TokenProvider;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends GenericFilter {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    private final TokenProvider tokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info(((HttpServletRequest) request).getRequestURI());

//        String accessToken = resolveToken((HttpServletRequest) request);
//        if (tokenProvider.validateToken(accessToken)) saveAuthentication(accessToken);
        chain.doFilter(request, response);
    }

    private void saveAuthentication(String jwt) {
        Authentication authentication = tokenProvider.getAuthentication(jwt);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.debug("Security Context에 '{}' 인증 정보를 저장했습니다, uri: {}", authentication.getName());
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        throw new JwtException("토큰이 없습니다.");
    }
}
