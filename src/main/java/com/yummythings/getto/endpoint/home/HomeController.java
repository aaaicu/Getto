package com.yummythings.getto.endpoint.home;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<String> printTest() {
        return ResponseEntity.ok("hello");
    }
}
