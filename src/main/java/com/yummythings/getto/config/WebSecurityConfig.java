package com.yummythings.getto.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();
//        http.headers().frameOptions().disable();

        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
//                .loginPage("/user/login")
//                .failureUrl("/user/login/error")
                .defaultSuccessUrl("/")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }
}
