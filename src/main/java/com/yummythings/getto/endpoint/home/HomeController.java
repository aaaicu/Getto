package com.yummythings.getto.endpoint.home;

import com.yummythings.getto.common.annotation.AuthCheck;
import com.yummythings.getto.common.annotation.AuthMember;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class HomeController {

    @AuthCheck
    @GetMapping("/")
    public String printHomeTest() {
        return "HOME!!";
    }

    @AuthCheck
    @GetMapping("/hello")
    public ResponseEntity<String> printTest(HttpServletRequest request, HttpServletResponse response, @AuthMember String authMember) {
        System.out.println("authMember = " + authMember);
        return ResponseEntity.ok("hello");
    }
}
