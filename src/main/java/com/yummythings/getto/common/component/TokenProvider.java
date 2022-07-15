package com.yummythings.getto.common.component;

import com.yummythings.getto.dto.TokenDTO;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Component
@Slf4j
public class TokenProvider implements InitializingBean {

    private static final String AUTHORITIES_KEY = "auth";
    private final String secret;
    private final long accessTokenExpirationMilliseconds;
    private final long refreshTokenExpirationMilliseconds;

    private Key key;
    private static final String JWT_ISSUER = "getto-aaaicu";

    public TokenProvider(@Value("${jwt.secret}") String secret,
                         @Value("${jwt.access-token-expiration-seconds}") long accessTokenExpirationSeconds,
                         @Value("${jwt.refresh-token-expiration-seconds}") long refreshTokenExpirationSeconds) {
        this.secret = secret;
        this.accessTokenExpirationMilliseconds = accessTokenExpirationSeconds * 1000;
        this.refreshTokenExpirationMilliseconds  = refreshTokenExpirationSeconds * 1000;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public TokenDTO createTokenByKakao(Authentication authentication,String kakaoAccessToken, String kakaoRefreshToken) {
        return TokenDTO.builder()
                .accessToken(createKakaoAccessToken(authentication, kakaoAccessToken))
                .refreshToken(createKakaoRefreshToken(authentication, kakaoRefreshToken))
                .build();
    }

    private String createKakaoAccessToken(Authentication authentication, String kakaoAccessToken) {
        return this.createToken(authentication,
                Map.of( "auth_organization", "KAKAO",
                        "access_token", kakaoAccessToken),
                accessTokenExpirationMilliseconds);
    }

    private String createKakaoRefreshToken(Authentication authentication, String kakaoRefreshToken) {
        return this.createToken(authentication,
                Map.of( "auth_organization", "KAKAO",
                        "refresh_token", kakaoRefreshToken),
                refreshTokenExpirationMilliseconds);
    }

    private String createToken(Authentication authentication, Map<String, Object> claims, Long expirationMilliseconds) {
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        Date expiration = new Date((new Date()).getTime() + expirationMilliseconds);

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .setIssuedAt(new Date())
                .setIssuer(JWT_ISSUER)
                .setExpiration(expiration)
                .addClaims(claims)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        Collection<? extends GrantedAuthority> authorities = parsingAuthorities(claims);
        User principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    private List<SimpleGrantedAuthority> parsingAuthorities(Claims claims) {
        return Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    public boolean validateToken(String token) {
        if (token == null) {
            return false;
        }

        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            log.info("JWT 토큰이 잘못되었습니다.");
        }
        return true;
    }
}
