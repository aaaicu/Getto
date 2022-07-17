package com.yummythings.getto.endpoint.oauth;

import com.yummythings.getto.dto.LoginResponseDTO;
import com.yummythings.getto.dto.TokenDTO;
import com.yummythings.getto.service.JwtService;
import com.yummythings.getto.service.UserAuthService;
import com.yummythings.getto.service.dto.KakaoAuthInfoDTO;
import com.yummythings.getto.service.dto.KakaoAuthResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/oauth")
public class OAuthController {

    private final UserAuthService userAuthService;
    private final JwtService jwtService;

    @ResponseBody
    @GetMapping("/kakao")
    public ResponseEntity<LoginResponseDTO> kakaoLoginCallback(@RequestParam String code, HttpServletResponse response) {

        // 넘어온 인가 코드로 access_token 발급
        KakaoAuthResponseDTO kakaoAuth = userAuthService.getAuthResponse(code);

        // 인증 사용자 정보
        KakaoAuthInfoDTO authUserInfo = userAuthService.getAuthUserInfo(kakaoAuth.getAccessToken());
        TokenDTO jwtToken = jwtService.getJwtToken(kakaoAuth, authUserInfo);

        LoginResponseDTO loginResponseDTO = createLoginResponseDTO(authUserInfo, jwtToken);
        response.setHeader("Set-Cookie", createRefreshTokenCookie(jwtToken.getRefreshToken()));

        log.debug("authUserInfo = " + authUserInfo);

        return ResponseEntity.ok(loginResponseDTO);
    }

    private LoginResponseDTO createLoginResponseDTO(KakaoAuthInfoDTO authUserInfo, TokenDTO jwtToken) {
        return LoginResponseDTO.builder()
                .accessToken(jwtToken.getAccessToken())
                .nickname(authUserInfo.getKakaoAccount().getProfile().getNickname())
                .thumbnailImageUrl(authUserInfo.getKakaoAccount().getProfile().getProfileImageUrl())
                .build();
    }

    private String createRefreshTokenCookie(String refreshToken) {
        ResponseCookie cookie = ResponseCookie.from("refreshToken", refreshToken)
                .maxAge(14 * 24 * 60 * 60)
                .path("/")
                .secure(true)
                .sameSite("None")
                .httpOnly(true)
                .build();
        return cookie.toString();
    }

}
