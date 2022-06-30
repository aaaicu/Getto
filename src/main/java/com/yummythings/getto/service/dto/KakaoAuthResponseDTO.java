package com.yummythings.getto.service.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KakaoAuthResponseDTO {
    private String accessToken;
    private String tokenType;
    private String refreshToken;
    private String idToken;
    private int expiresIn;
    private List<String> scope;
    private int refreshTokenExpiresIn;

    @JsonCreator
    public KakaoAuthResponseDTO(@JsonProperty("scope") String scope) {
        this.scope = parseScope(scope);
    }

    public List<String> parseScope(String scopeValue) {
        String[] scopes = scopeValue.split(" ");
        return Arrays.stream(scopes).map(String::trim).collect(Collectors.toList());
    }
}
