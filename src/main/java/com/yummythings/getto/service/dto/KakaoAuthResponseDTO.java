package com.yummythings.getto.service.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KakaoAuthResponseDTO {
    protected String accessToken;
    protected String refreshToken;
    protected String idToken;
    private String tokenType;
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
