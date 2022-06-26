package com.yummythings.getto.property;

import com.yummythings.getto.service.dto.AuthorizationCodeDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.MultiValueMap;

@Getter
@Setter
@Configuration
@ConfigurationProperties("oauth")
public class OAuthUrlProperty {
    private final static String AUTHORIZATION_CODE_TYPE  = "authorization_code";

    private String hostUrl;
    private Kakao kakao;

    @Setter
    @Getter
    public static class Kakao{
    private String restApiKey;
    private String url;
    }

    public MultiValueMap<String, String> getAuthorizationCodeDTO(String authorizationCode) {
        return AuthorizationCodeDTO.toParams(AuthorizationCodeDTO.builder()
                .grantType(AUTHORIZATION_CODE_TYPE)
                .clientId(this.kakao.restApiKey)
                .redirectUri(this.hostUrl+ this.kakao.url)
                .code(authorizationCode)
                .build());
    }

}
