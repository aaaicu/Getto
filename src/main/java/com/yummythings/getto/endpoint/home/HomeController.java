package com.yummythings.getto.endpoint.home;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    @GetMapping("/")
    public String printHomeTest() {
        return "HOME!!";
    }

    @GetMapping("/test")
    public String printTest() {
        return "test";
    }
}
