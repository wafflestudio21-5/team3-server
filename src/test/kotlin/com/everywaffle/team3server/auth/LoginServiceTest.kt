package com.everywaffle.team3server.auth

import com.everywaffle.team3server.user.dto.LocalLoginRequest
import com.everywaffle.team3server.user.model.UserEntity
import com.everywaffle.team3server.user.repository.UserRepository
import com.everywaffle.team3server.user.service.UserServiceImpl
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.kotlin.whenever
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.crypto.password.PasswordEncoder


@SpringBootTest
class LoginServiceTest {
    @Mock
    private lateinit var userRepository: UserRepository

    @Mock
    private lateinit var passwordEncoder: PasswordEncoder

    @Mock
    private lateinit var jwtTokenProvider: JwtTokenProvider

    private lateinit var userService: UserServiceImpl

    @BeforeEach
    fun setUp() {
        userService = UserServiceImpl(userRepository, passwordEncoder, jwtTokenProvider)
    }
    @Test
    fun `자체 로그인 시 유효한 자격의 경우 Null 반환하지 않아야 한다`() {
        val loginRequest = LocalLoginRequest(id = "user", password = "password")
        val userEntity = UserEntity(userName = loginRequest.id, password = "encodedPassword", email = "dummy@email.com")
        val jwtToken = "jwtToken"

        whenever(userRepository.findByUserId(loginRequest.id)).thenReturn(userEntity)
        whenever(passwordEncoder.matches(loginRequest.password, userEntity.password)).thenReturn(true)
        whenever(jwtTokenProvider.createToken(loginRequest.id)).thenReturn(jwtToken)

        val result = userService.localLogin(loginRequest)

        assertNotNull(result)
        result?.let {
            assert(it.userId == loginRequest.id)
            assert(it.token == jwtToken)
        }
    }
    @Test
    fun `자체 로그인 시 유효하지 않은 자격의 경우 Null을 반환해야한다`() {
        val loginRequest = LocalLoginRequest(id = "user", password = "wrongPassword")

        whenever(userRepository.findByUserId(loginRequest.id)).thenReturn(null)

        val result = userService.localLogin(loginRequest)

        assertNull(result, "No response should be returned for invalid credentials")
    }
}
