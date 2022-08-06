package com.yummythings.getto.dto;

import com.yummythings.getto.endpoint.oauth.response.LoginResponseDTO;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
    private TokenDTO loginToken;
    private String nickname;
    private String thumbnailImageUrl;

    public static LoginResponseDTO extract(LoginDTO loginDTO) {
        return LoginResponseDTO.builder()
                .accessToken(loginDTO.getLoginToken().getAccessToken())
                .refreshToken(loginDTO.getLoginToken().getRefreshToken())
                .nickname(loginDTO.nickname)
                .thumbnailImageUrl(loginDTO.thumbnailImageUrl)
                .build();
    }

}
