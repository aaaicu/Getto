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
        return httpUtil.post(
                REQUEST_ACCESS_TOKEN_URL,
                httpUtil.getDefaultHeader(),
                oAuthUrlProperty.getAuthorizationCodeDTO(authorizationCode),
                String.class).getBody();
    }
}
