package com.minnphuc.socialapp.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/user/**").permitAll()
                        .requestMatchers("/post/**").permitAll()
                        .anyRequest().permitAll()
                );
        http.cors().and().csrf().disable().httpBasic();

        return http.build();
    }
}
