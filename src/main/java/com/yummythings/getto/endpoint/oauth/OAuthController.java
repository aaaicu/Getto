package com.yummythings.getto.endpoint.oauth;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth")
public class OAuthController {

    @ResponseBody
    @GetMapping("/kakao")
    public void kakaoCallback(@RequestParam String code) {
        System.out.println(code);
    }
}
