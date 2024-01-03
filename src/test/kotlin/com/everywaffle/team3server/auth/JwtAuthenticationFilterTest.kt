package com.everywaffle.team3server.auth

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletResponse
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.mock
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.kotlin.whenever
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.mock.web.MockHttpServletResponse

@SpringBootTest
class JwtAuthenticationFilterTest {
    private lateinit var jwtTokenProvider: JwtTokenProvider
    private lateinit var jwtAuthenticationFilter: JwtAuthenticationFilter
    private lateinit var request: MockHttpServletRequest
    private lateinit var response: MockHttpServletResponse
    private lateinit var filterChain: FilterChain

    @BeforeEach
    fun setUp() {
        // JwtTokenProvider에 대한 테스트 코드는 따로 있으므로, Mock 객체로 Filter 테스트 진행
        jwtTokenProvider = mock(JwtTokenProvider::class.java)
        jwtAuthenticationFilter = JwtAuthenticationFilter(jwtTokenProvider)
        request = MockHttpServletRequest()
        response = MockHttpServletResponse()
        filterChain = mock(FilterChain::class.java)
    }

    @Test
    fun `When path is excluded, filter should not check token`() {
        // 토큰 검증 대상 path가 아닌 경우, filter가 token을 체크하지 않는지 확인
        request.requestURI = "/api/signin"

        jwtAuthenticationFilter.doFilter(request, response, filterChain)

        verify(filterChain).doFilter(request, response)
        verify(jwtTokenProvider, never()).validateToken(anyString())
    }

    @Test
    fun `When valid token is provided, filter should allow request`() {
        // valid한 token에 대해 request를 allow하는지 확인
        request.pathInfo = "/api/test"
        request.addHeader("Authorization", "Bearer validToken")
        whenever(jwtTokenProvider.validateToken("validToken")).thenReturn(true)

        jwtAuthenticationFilter.doFilter(request, response, filterChain)

        verify(filterChain).doFilter(request, response)
    }

    @Test
    fun `When invalid token is provided, filter should reject request`() {
        // invalid한 token에 대해 request를 reject하는지 확인
        request.pathInfo = "/api/test"
        request.addHeader("Authorization", "Bearer invalidToken")
        whenever(jwtTokenProvider.validateToken("invalidToken")).thenReturn(false)

        jwtAuthenticationFilter.doFilter(request, response, filterChain)

        verify(filterChain, never()).doFilter(request, response)
        assert(response.status == HttpServletResponse.SC_UNAUTHORIZED)
    }
}