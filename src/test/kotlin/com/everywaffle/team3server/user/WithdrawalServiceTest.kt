package com.everywaffle.team3server.user

import com.everywaffle.team3server.user.service.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
class WithdrawalServiceTest @Autowired constructor(
    private val userSignUpService: UserSignUpService,
    private val userSignInService: UserSignInService,
    private val userWithdrawalService: UserWithdrawalService
) {

    @BeforeEach
    fun init() {
        userSignUpService.signUp(
            userName = "every",
            password = "waffle",
            email = "hello@everywaffle.com"
        )
    }

    @Test
    fun `회원 찾기 실패`() {
        val userName = "everyy"
        val password = "waffle"

        assertThrows<UserNotFoundException> {
            userWithdrawalService.withdrawal(
                userName,
                password
            )
        }
    }

    @Test
    fun `비밀번호 인증 실패`() {
        val userName = "every"
        val password = "wafflee"

        assertThrows<WithdrawalInvalidPasswordException> {
            userWithdrawalService.withdrawal(
                userName,
                password
            )
        }
    }

    @Test
    fun `회원 탈퇴 성공`() {
        val userName = "every"
        val password = "waffle"

        assertDoesNotThrow {
            userWithdrawalService.withdrawal(
                userName,
                password
            )
        }

        assertThrows<SignInUserNameNotFoundException> {
            userSignInService.localSignIn(
                userName,
                password
            )
        }
    }

}