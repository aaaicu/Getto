package com.yummythings.getto.common.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;

@Component
@Slf4j
public class CookieUtil {
    public String createRefreshTokenCookie(String refreshToken) {
        final int twoWeeks = 14 * 24 * 60 * 60;

        ResponseCookie cookie = ResponseCookie.from("refreshToken", refreshToken)
                .maxAge(twoWeeks)
                .path("/")
                .secure(true)
                .sameSite("None")
                .httpOnly(true)
                .build();
        return cookie.toString();
    }
}
