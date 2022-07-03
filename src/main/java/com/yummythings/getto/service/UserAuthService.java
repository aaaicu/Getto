package com.yummythings.getto.service;

import com.yummythings.getto.component.HttpUtil;
import com.yummythings.getto.property.OAuthUrlProperty;
import com.yummythings.getto.service.dto.KakaoAuthResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserAuthService {
    private final static String REQUEST_ACCESS_TOKEN_URL = "https://kauth.kakao.com/oauth/token";
    private final static String REQUEST_PROFILE_URL = "https://kapi.kakao.com/v2/user/me";

    private final OAuthUrlProperty oAuthUrlProperty;

    private final HttpUtil httpUtil;

    public KakaoAuthResponseDTO getAuthResponse(String authorizationCode) {
        return httpUtil.post(
                REQUEST_ACCESS_TOKEN_URL,
                httpUtil.getDefaultHeader(),
                oAuthUrlProperty.getAuthorizationCodeDTO(authorizationCode),
                KakaoAuthResponseDTO.class).getBody();
    }

    public void saveAuthRefreshToken(String refreshToken, int refreshTokenExpiresIn) {
        log.info("리프래시 토큰 저장할 거임");
    }
}
