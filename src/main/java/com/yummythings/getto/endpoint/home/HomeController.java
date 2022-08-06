package com.yummythings.getto.endpoint.home;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class HomeController {

    @GetMapping("/")
    public String printHomeTest() {
        return "HOME!!";
    }

    @GetMapping("/hello")
    public ResponseEntity<String> printTest(HttpServletRequest request, HttpServletResponse response) {

        Cookie[] cookies = request.getCookies();

        ResponseCookie cookie = ResponseCookie.from("myCookie", "myCookieValue") // key & value
//                .httpOnly(true)
//                .secure(true)
//                .domain("localhost")  // host
                .path("/")      // path
                .maxAge(Duration.ofHours(1))
                .sameSite("None")  // sameSite
                .build();

        response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());

//        response.setHeader("Set-Cookie", "test!!");
        return ResponseEntity.ok("hello");
    }
}
