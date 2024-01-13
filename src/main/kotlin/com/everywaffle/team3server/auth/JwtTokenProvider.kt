package com.everywaffle.team3server.auth

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.time.Instant
import java.util.Date

@Component
class JwtTokenProvider(
    @Value("\${jwt.secret-key}") private val secretKey: String,
) {
    private val algorithm: Algorithm = Algorithm.HMAC256(secretKey)

    fun createToken(username: String): String {
        // JWT 생성(생성 시간, 만료 시간, username 이용)
        try {
            val issuedAt = Date.from(Instant.now())
            val expiredAt = Date.from(Instant.now().plusMillis(1000 * 3600 * 100)) // 만료 시간: 100시간

            return JWT.create()
                .withClaim("username", username)
                .withIssuedAt(issuedAt)
                .withExpiresAt(expiredAt)
                .sign(algorithm)
        } catch (e: Exception) {
            throw JwtCreationException("Failed to create JWT: ${e.message}")
        }
    }

    fun validateToken(token: String): Boolean {
        // JWT에 대해 유효한지 검증
        try {
            val verifier = JWT.require(algorithm).build()
            verifier.verify(token)
            return true
        } catch (e: JWTVerificationException) {
            throw JwtValidationException("Failed to validate JWT: ${e.message}")
        }
    }

    fun getAuthentication(token: String): Authentication {

        try {
            val username = JWT.require(algorithm)
                .build()
                .verify(token)
                .claims["username"]!!
                .asString()
            return UsernamePasswordAuthenticationToken(username, token)

        } catch (e: RuntimeException) {
            throw JwtAuthenticationException("Failed to get authentication")
        }
    }

}