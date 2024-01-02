package com.everywaffle.team3server.user

import com.everywaffle.team3server.auth.JwtTokenProvider
import com.everywaffle.team3server.user.service.SignUpEmailConflictException
import com.everywaffle.team3server.user.service.SignUpUsernameConflictException
import com.everywaffle.team3server.user.service.UserService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
class SignUpServiceTest @Autowired constructor(
    private val userService: UserService,
    private val jwtTokenProvider: JwtTokenProvider,
){
    @Test
    @Transactional
    fun `회원가입 정상 작동 확인`(){
        val user = assertDoesNotThrow {
            userService.signUp(
                userName = "test-${javaClass.name}-1",
                password = "spring",
                email = "waffle@everywaffle.com"
            )
        }
        assertThat(user.userName).isEqualTo("test-${javaClass.name}-1")
        assertThat(user.email).isEqualTo("waffle@everywaffle.com")
        assertDoesNotThrow {
            jwtTokenProvider.validateToken(user.token)
        }
    }

    @Test
    @Transactional
    fun `회원가입 시 username 충돌하면 예외 처리`() {
        assertDoesNotThrow {
            userService.signUp(
                userName = "test-${javaClass.name}-1",
                password = "spring",
                email = "waffle@everywaffle.com"
            )
        }

        assertThrows<SignUpUsernameConflictException> {
            userService.signUp(
                userName = "test-${javaClass.name}-1",
                password = "spring",
                email = "waffle1@everywaffle.com"
            )
        }
    }

    @Test
    @Transactional
    fun `회원가입 시 email 충돌하면 예외 처리`() {
        assertDoesNotThrow {
            userService.signUp(
                userName = "test-${javaClass.name}-1",
                password = "spring",
                email = "waffle@everywaffle.com"
            )
        }

        assertThrows<SignUpEmailConflictException> {
            userService.signUp(
                userName = "test-${javaClass.name}-2",
                password = "spring",
                email = "waffle@everywaffle.com"
            )
        }
    }



}