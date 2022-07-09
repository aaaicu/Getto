package com.yummythings.getto.endpoint.oauth;

import com.yummythings.getto.dto.TokenDTO;
import com.yummythings.getto.service.JwtService;
import com.yummythings.getto.service.UserAuthService;
import com.yummythings.getto.service.dto.KakaoAuthInfoDTO;
import com.yummythings.getto.service.dto.KakaoAuthResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/oauth")
public class OAuthController {

    private final UserAuthService userAuthService;
    private final JwtService jwtService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @ResponseBody
    @GetMapping("/kakao")
    public ResponseEntity<TokenDTO> kakaoCallback(@RequestParam String code) {

        // 넘어온 인가 코드로 access_token 발급
        KakaoAuthResponseDTO kakaoAuth = userAuthService.getAuthResponse(code);
        KakaoAuthInfoDTO authUserInfo = userAuthService.getAuthUserInfo(kakaoAuth.getAccessToken());
        log.debug("authUserInfo = " + authUserInfo);
        return ResponseEntity.ok(jwtService.getJwtToken(authUserInfo));


//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken()

//        authenticationManagerBuilder.getObject().authenticate()




        // refresh_token 저장
//        userAuthService.saveAuthRefreshToken(kakaoAuth.getRefreshToken(), kakaoAuth.getRefreshTokenExpiresIn());



        // access_token -> 암호화 처리 필요


        // access_token을 포함한 JWT 를 생성

        // JWT 토큰발급




    }

}
