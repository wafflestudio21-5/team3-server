package com.everywaffle.team3server.user

import com.everywaffle.team3server.auth.JwtTokenProvider
import com.everywaffle.team3server.user.dto.LocalSignInRequest
import com.everywaffle.team3server.user.model.UserEntity
import com.everywaffle.team3server.user.repository.UserRepository
import com.everywaffle.team3server.user.service.SignInInvalidPasswordException
import com.everywaffle.team3server.user.service.SignInUserNameNotFoundException
import com.everywaffle.team3server.user.service.UserSignInServiceImpl
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.mockito.Mock
import org.mockito.kotlin.whenever
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
class SignInServiceTest {
    @Mock
    private lateinit var userRepository: UserRepository

    @Mock
    private lateinit var jwtTokenProvider: JwtTokenProvider

    private lateinit var userService: UserSignInServiceImpl

    @BeforeEach
    fun setUp() {
        userService = UserSignInServiceImpl(userRepository, jwtTokenProvider)
    }
    @Test
    fun `유효한 자격에 대해 로그인 할 수 있어야 한다`() {
        val request = LocalSignInRequest(userName = "user", password = "password")
        val userEntity = UserEntity(userName = "user", password = "password", email = "user@example.com")
        val jwtToken = "jwtToken"

        whenever(userRepository.findByUserName(request.userName)).thenReturn(userEntity)
        whenever(jwtTokenProvider.createToken(request.userName)).thenReturn(jwtToken)

        val result = assertDoesNotThrow { userService.localSignIn(request.userName, request.password) }

        assertNotNull(result)
        assertEquals(userEntity.userId, result.userId)
        assertEquals(userEntity.userName, result.userName)
        assertEquals(userEntity.email, result.email)
        assertEquals(jwtToken, result.token)
    }
    @Test
    fun `유효하지 않은 이름에 대해 자체 로그인은 SignInUserNameNotFoundException을 Throw 해야 한다`() {
        val request = LocalSignInRequest(userName = "invalidUser", password = "password")

        whenever(userRepository.findByUserName(request.userName)).thenReturn(null)

        assertThrows<SignInUserNameNotFoundException> {
            userService.localSignIn(request.userName, request.password)
        }
    }
    @Test
    fun `유효하지 않은 비밀번호에 대해 자체 로그인은 SignInInvalidPasswordException을 Throw 해야 한다`() {
        val request = LocalSignInRequest(userName = "user", password = "wrongPassword")
        val userEntity = UserEntity(userName = "user", password = "password", email = "user@example.com")

        whenever(userRepository.findByUserName(request.userName)).thenReturn(userEntity)

        assertThrows<SignInInvalidPasswordException> {
            userService.localSignIn(request.userName, request.password)
        }
    }


}
