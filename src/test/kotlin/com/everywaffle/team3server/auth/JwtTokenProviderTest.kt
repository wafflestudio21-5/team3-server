package com.everywaffle.team3server.auth

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class JwtTokenProviderTest
@Autowired
constructor(
    private val jwtTokenProvider: JwtTokenProvider,
) {
    @Test
    fun `Given valid username, when createToken is called, then it should return a non-null token`() {
        // 실제 username에 대해 적절한 token이 생성되는지 확인
        val username = "testUser"
        val token = jwtTokenProvider.createToken(username)
        assertNotNull(token, "Token should not be null")
    }

    @Test
    fun `Given a valid token, when validateToken is called, then it should return true`() {
        // username에 대해 토큰을 생성하고, 생성된 토큰에 대해 validation 진행
        val username = "testUser"
        val token = jwtTokenProvider.createToken(username)
        assertTrue(jwtTokenProvider.validateToken(token), "Token should be valid")
    }

    @Test
    fun `Given an invalid token, when validateToken is called, then it should throw JwtValidationException`() {
        // 존재하지 않는 token에 대해 validation 진행
        val invalidToken = "invalidToken"
        val exception =
            assertThrows<JwtValidationException> {
                jwtTokenProvider.validateToken(invalidToken)
            }
        assertNotNull(exception.message)
    }
}