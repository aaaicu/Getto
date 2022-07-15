package com.yummythings.getto.endpoint.oauth;

import com.yummythings.getto.common.constant.AccessableClient;
import com.yummythings.getto.dto.TokenDTO;
import com.yummythings.getto.service.JwtService;
import com.yummythings.getto.service.UserAuthService;
import com.yummythings.getto.service.dto.KakaoAuthInfoDTO;
import com.yummythings.getto.service.dto.KakaoAuthResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.web.bind.annotation.*;

import static com.yummythings.getto.common.constant.AccessableClient.CORS_ALLOW_URL;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/oauth")
public class OAuthController {

    private final UserAuthService userAuthService;
    private final JwtService jwtService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @CrossOrigin(originPatterns = CORS_ALLOW_URL )
    @ResponseBody
    @GetMapping("/kakao")
    public ResponseEntity<TokenDTO> kakaoCallback(@RequestParam String code) {

        // 넘어온 인가 코드로 access_token 발급
        KakaoAuthResponseDTO kakaoAuth = userAuthService.getAuthResponse(code);

        // 인증 사용자 정보
        KakaoAuthInfoDTO authUserInfo = userAuthService.getAuthUserInfo(kakaoAuth.getAccessToken());

        log.debug("authUserInfo = " + authUserInfo);

        return ResponseEntity.ok(jwtService.getJwtToken(kakaoAuth, authUserInfo));
    }

}
