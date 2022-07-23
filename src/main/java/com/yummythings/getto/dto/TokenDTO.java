package com.yummythings.getto.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class TokenDTO {
    private String accessToken;
    private String refreshToken;
}
