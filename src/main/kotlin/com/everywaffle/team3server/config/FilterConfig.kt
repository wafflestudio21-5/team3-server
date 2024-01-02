package com.everywaffle.team3server.config

import com.everywaffle.team3server.auth.JwtAuthenticationFilter
import com.everywaffle.team3server.auth.JwtTokenProvider
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FilterConfig(private val jwtTokenProvider: JwtTokenProvider) {
    // JWT 인증 로직 필터에 추가, 보호할 url 패턴 따로 지정
    @Bean
    fun jwtFilterRegistrationBean(): FilterRegistrationBean<JwtAuthenticationFilter> {
        val registrationBean = FilterRegistrationBean<JwtAuthenticationFilter>()
        registrationBean.filter = JwtAuthenticationFilter(jwtTokenProvider)
        registrationBean.addUrlPatterns("/api/**") // 보호할 URL 패턴 지정
        return registrationBean
    }
}