package com.yummythings.getto.common.config;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

//@Configuration
public class corsConfig {
//    @Bean("customCorsConfig")
//    public UrlBasedCorsConfigurationSource customCorsConfig() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
////        config.addAllowedOriginPattern("http://localhost:3000");
////        config.addAllowedOriginPattern("https://*.yummy-things.xyz");
//        config.addAllowedHeader("*");
//        config.addAllowedMethod("*");
//        config.addExposedHeader("Authorization");
//        config.addExposedHeader("refreshToken");
//        source.registerCorsConfiguration("/**",config);
//        return source;
//    }

}
