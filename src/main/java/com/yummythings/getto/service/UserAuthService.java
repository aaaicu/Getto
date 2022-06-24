package com.yummythings.getto.service;

import com.yummythings.getto.component.HttpUtil;
import com.yummythings.getto.property.OAuthUrlProperty;
import com.yummythings.getto.service.dto.AuthorizationCodeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

@Service
@RequiredArgsConstructor
public class UserAuthService {
    private final static String REQUEST_ACCESS_TOKEN_URL = "https://kauth.kakao.com/oauth/token";
    private final static String REQUEST_PROFILE_URL = "https://kapi.kakao.com/v2/user/me";

    private final OAuthUrlProperty oAuthUrlProperty;

    private final HttpUtil httpUtil;
    public String getAccessToken(String authorizationCode) {
        HttpHeaders defaultHeader = httpUtil.getDefaultHeader();
        return httpUtil.post(REQUEST_ACCESS_TOKEN_URL, defaultHeader, getParams(authorizationCode), String.class).getBody();
    }

    private MultiValueMap<String, String> getParams(String authorizationCode) {
        return AuthorizationCodeDTO.toParams(AuthorizationCodeDTO.builder()
                .grantType("authorization_code")
                .clientId(oAuthUrlProperty.getKakao().getRestApiKey())
                .redirectUri(oAuthUrlProperty.getHostUrl()+ "/oauth/kakao")
                .code(authorizationCode)
                .build());
    }


}
