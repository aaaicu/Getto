package com.yummythings.getto.common.config;

import com.yummythings.getto.common.property.FrontInfoProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@RequiredArgsConstructor
public class CorsConfig {

    private final FrontInfoProperty frontInfoProperty;

    @Bean("customCorsConfig")
    public CorsConfigurationSource customCorsConfig() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern(frontInfoProperty.getHostUrl());
        config.addAllowedOriginPattern("https://yummy-things.xyz");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.addExposedHeader("Authorization");
        config.addExposedHeader("refreshToken");
        source.registerCorsConfiguration("/**",config);

        return source;
    }



}
