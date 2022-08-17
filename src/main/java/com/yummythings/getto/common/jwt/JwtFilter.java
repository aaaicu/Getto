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
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends GenericFilter {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    private final TokenProvider tokenProvider;
    private static final List<String> exceptUrls = List.of("/oauth/kakao","/auth/reissue");

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;

//        String requestURI = req.getRequestURI();
//
//        try {
//            if (!exceptUrls.contains(requestURI)) {
//                String accessToken = resolveToken(req);
//                if (tokenProvider.validateToken(accessToken))
//                    saveAuthentication(accessToken);
//            }
//            chain.doFilter(req, res);
//        } catch (Exception e) {
//            res.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
//        }

        chain.doFilter(req, res);
    }

//    private void saveAuthentication(String jwt) {
//        Authentication authentication = tokenProvider.getAuthentication(jwt);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        log.debug("Security Context에 '{}' 인증 정보를 저장했습니다, uri: {}", authentication.getName());
//    }
//
//    private String resolveToken(HttpServletRequest request) {
//        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
//        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
//            return bearerToken.substring(7);
//        }
//        throw new JwtException("토큰이 없습니다.");
//    }
}
