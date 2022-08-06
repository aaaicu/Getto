package com.yummythings.getto.endpoint.oauth.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDTO {
    private String accessToken;
    private String refreshToken;
    private String nickname;
    private String thumbnailImageUrl;
}
