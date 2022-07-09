package com.yummythings.getto.config;

import com.yummythings.getto.component.TokenProvider;
import com.yummythings.getto.jwt.JwtAccessDeniedHandler;
import com.yummythings.getto.jwt.JwtAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/h2-console/**", "/favicon.ico");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()

                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                // h2콘솔을 위한 설정
                .and()
                .headers()
                .frameOptions()
                .sameOrigin()

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                .antMatchers("/api/hello").permitAll() // 인증 허용 대상 패턴
                .antMatchers("/api/authenticate").permitAll() // 인증 허용 대상 패턴
                .antMatchers("/oauth/kakao").permitAll() // 인증 허용 대상 패턴
                .antMatchers("/oauth/authorize").permitAll() // 인증 허용 대상 패턴
                .antMatchers("/api/signup").permitAll() // 인증 허용 대상 패턴
                .antMatchers("/error").permitAll() // 인증 허용 대상 패턴
                .anyRequest().authenticated() // 나머지 요청은 인증 필요

                .and()
                .apply(new JwtSecurityConfig(tokenProvider));
    }

    //    @Override
//    protected void configure(HttpSecurity http) throws Exception {
////        http.csrf().disable();
////        http.headers().frameOptions().disable();
//
//        http.authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
////                .loginPage("/user/login")
////                .failureUrl("/user/login/error")
//                .defaultSuccessUrl("/")
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll();
//    }
}
