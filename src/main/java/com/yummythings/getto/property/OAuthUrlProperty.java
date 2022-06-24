package com.yummythings.getto.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties("oauth")
public class OAuthUrlProperty {
    private String hostUrl;
    private Kakao kakao;

    @Setter
    @Getter
    public static class Kakao{
    private String restApiKey;
    }

}
