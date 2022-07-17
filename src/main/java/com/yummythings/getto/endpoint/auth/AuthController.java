package com.yummythings.getto.endpoint.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/auth")
public class AuthController {
    @GetMapping
    public ResponseEntity<String> testAuth(
            @CookieValue("refreshToken") String refreshToken) {
        System.out.println("refreshToken = " + refreshToken);

        return ResponseEntity.ok("ok");
    }
}
