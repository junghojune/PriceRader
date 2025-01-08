package com.cha.priceradar.security.config;

import com.cha.priceradar.security.filter.JwtAuthorizationFilter;
import com.cha.priceradar.security.handlers.CustomAccessDeniedHandler;
import com.cha.priceradar.security.handlers.CustomAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class PriceRaderSecurityConfiguration {
    private final JwtAuthorizationFilter jwtAuthorizationFilter;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .formLogin(AbstractHttpConfigurer::disable)

                .csrf(AbstractHttpConfigurer::disable)

                .sessionManagement(AbstractHttpConfigurer::disable)

                .addFilterBefore(
                        jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)

                .authorizeHttpRequests((authorizeHttpRequests) ->
                        authorizeHttpRequests
                                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll() // resources 접근 허용 설정
                                .requestMatchers("/").permitAll() // 메인 페이지 요청 허가
                                .requestMatchers("/api/user/**").permitAll() // '/api/user/'로 시작하는 요청 모두 접근 허가
                                .anyRequest().authenticated() // 그 외 모든 요청 인증처리
                )

                .exceptionHandling(securityExceptionHandler->
                        securityExceptionHandler
                                .accessDeniedHandler(customAccessDeniedHandler)
                                .authenticationEntryPoint(customAuthenticationEntryPoint))

                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
        ;

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
