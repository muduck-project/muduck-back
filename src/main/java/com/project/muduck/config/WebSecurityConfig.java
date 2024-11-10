package com.project.muduck.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.project.muduck.handler.OAuth2SuccessHandler;

import lombok.RequiredArgsConstructor;

@Configurable
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final OAuth2SuccessHandler oAuth2SuccessHandler;
    
    @Bean
    protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        
        httpSecurity
            .cors(cors -> cors
                .configurationSource(corsConfigurationSource()))
                .csrf(CsrfConfigurer::disable)
                .httpBasic(HttpBasicConfigurer::disable)
                .sessionManagement(sessionManagement -> sessionManagement
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(request -> request

                                
                                .requestMatchers("/", "/api/v1/auth/sign-in/*", "/api/v1/auth/**", "/file/*", "/api/v1/recruit/*/comments", "/api/v1/active","/api/v1/active/*/comments",
                                                "/upload/*","/find-id/*", "/send-auth/*","/api/v1/recruit","/api/v1/recruit/**","/api/v1/recruit/join/*","/api/v1/qna","/api/v1/qna/*","/api/v1/qna/*/comments",
                                                "/api/v1/active/*", "/oauth2/callback/*","/reports", "/password-send-auth","./file", "./upload","/api/v1/active/like/*",
                                                "/api/v1/recruit/iscompleted/*", "/api/v1/recruit/scrap", "/api/v1/recruit/scrap/*", "/api/v1/follow", "/api/v1/follow/**", "/api/v1/recruit/cityPostCounts").permitAll()
                                .requestMatchers("/api/v1/user/**").hasRole("USER")
                                .requestMatchers("/api/v1/admin/**").hasRole("ADMIN")
                                .anyRequest().authenticated())
                .exceptionHandling(exceptionHandling -> exceptionHandling
                                .authenticationEntryPoint(new FailedAuthenticationEntryPoint()))

    }

}
