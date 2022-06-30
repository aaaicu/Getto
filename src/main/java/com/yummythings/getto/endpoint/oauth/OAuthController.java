package com.yummythings.getto.endpoint.oauth;

import com.yummythings.getto.service.UserAuthService;
import com.yummythings.getto.service.dto.KakaoAuthResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/oauth")
public class OAuthController {

    private final UserAuthService userAuthService;

    @ResponseBody
    @GetMapping("/kakao")
    public ResponseEntity<String> kakaoCallback(@RequestParam String code) {

        // 넘어온 인가 코드로 access_token 발급
        KakaoAuthResponseDTO kakaoAuth = userAuthService.getAccessToken(code);
        log.info("kakaoAuth = " + kakaoAuth);

        // refresh_token 저장

        // access_token -> 암호화 처리 필요
        // access_token을 포함한 JWT 를 생성

        // JWT 토큰발급


        return ResponseEntity.ok("인증값 : " + kakaoAuth);
    }

}
