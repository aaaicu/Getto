package com.yummythings.getto.service.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Builder
@Getter
public class AuthorizationCodeDTO {
    private String grantType;
    private String clientId;
    private String redirectUri;
    private String code;


    public static MultiValueMap<String, String> toParams(AuthorizationCodeDTO dto) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", dto.grantType);
        params.add("client_id", dto.clientId);
        params.add("redirect_uri", dto.redirectUri);
        params.add("code", dto.code);

        return params;
    }

}
