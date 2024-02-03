package com.everywaffle.team3server.auth

import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

class JwtAuthenticationFilter(private val jwtTokenProvider: JwtTokenProvider) : Filter {
    override fun doFilter(
        request: ServletRequest,
        response: ServletResponse,
        chain: FilterChain,
    ) {
        val httpRequest = request as HttpServletRequest

        if (isExcludedPath(httpRequest.requestURI)) {
            chain.doFilter(request, response)
            return
        }

        val jwt = getJwtFromRequest(httpRequest)
        try {
            if (jwt != null && jwtTokenProvider.validateToken(jwt)) {
                // 유효한 토큰인 경우, 요청 처리
            } else {
                // 유효하지 않은 토큰인 경우, 에러 응답
                (response as HttpServletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT token")
                return
            }
        } catch (e: JwtValidationException) {
            // JWT 검증 실패 시 예외 처리
            (response as HttpServletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Validation failed")
            return
        }

        chain.doFilter(request, response)
    }

    private fun getJwtFromRequest(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader("Authorization")
        return if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            bearerToken.substring(7)
        } else {
            null
        }
    }

    private fun isExcludedPath(path: String): Boolean {
        return path.startsWith("/api/signin") || path.startsWith("/api/signup") || path.startsWith("/test-page") || path.contains("/swagger-ui/") || path.contains("/v3/api-docs") || path.contains("/login")
    }
}