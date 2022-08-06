package com.yummythings.getto.service;

import com.yummythings.getto.common.component.HttpUtil;
import com.yummythings.getto.common.property.OAuthUrlProperty;
import com.yummythings.getto.service.dto.KakaoAuthInfoDTO;
import com.yummythings.getto.service.dto.KakaoAuthResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class UserAuthService {
    private final static String REQUEST_ACCESS_TOKEN_URL = "https://kauth.kakao.com/oauth/token";
    private final static String REQUEST_PROFILE_URL = "https://kapi.kakao.com/v2/user/me";

    private final OAuthUrlProperty oAuthUrlProperty;

    private final HttpUtil httpUtil;

    /**
     * 사용자 인증
     *
     * @param authorizationCode 인증요청 코드
     * @return 인증 결과 반환
     */
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

    /**
     * 인증 사용자 정보 요청
     *
     * @param accessToken 사용자 인증 후 받은 액세스토큰
     * @return 인증 사용자 정보
     */
    public KakaoAuthInfoDTO getAuthUserInfo(String accessToken) {
        return httpUtil.post(
                REQUEST_PROFILE_URL,
                httpUtil.getAuthorizationHeader(accessToken),
                KakaoAuthInfoDTO.class).getBody();
    }
}