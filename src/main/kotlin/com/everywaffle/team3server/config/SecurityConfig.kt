package com.everywaffle.team3server.config

import OAuth2AuthenticationSuccessHandler
import com.everywaffle.team3server.auth.JwtAuthenticationFilter
import com.everywaffle.team3server.auth.JwtTokenProvider
import com.everywaffle.team3server.user.repository.UserRepository
import com.everywaffle.team3server.user.service.CustomOAuth2UserService
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
class SecurityConfig(
    private val jwtTokenProvider: JwtTokenProvider,
    private val customOAuth2UserService: CustomOAuth2UserService,
    private val userRepository: UserRepository,
) {
    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .httpBasic { it -> it.disable() }
            .csrf { it -> it.disable() }
            .sessionManagement { it -> it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authorizeHttpRequests { it ->
                it
                    .requestMatchers("/**").permitAll()
                    .requestMatchers("/welcome").permitAll()
                    .requestMatchers("/api/signup").permitAll()
                    .requestMatchers("/api/signin").permitAll()
                    .requestMatchers("/test-page").permitAll()
                    .requestMatchers("/swagger-ui/**").permitAll()
                    .requestMatchers("/v3/api-docs/**").permitAll()
                    .anyRequest().authenticated()
            }
            .addFilterBefore(JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter::class.java)
            // 소셜 로그인
            .oauth2Login {
                it.userInfoEndpoint {
                    it.userService(customOAuth2UserService)
                }
                    .successHandler(OAuth2AuthenticationSuccessHandler(jwtTokenProvider, ObjectMapper(), userRepository))
            }
        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder()
    }
}