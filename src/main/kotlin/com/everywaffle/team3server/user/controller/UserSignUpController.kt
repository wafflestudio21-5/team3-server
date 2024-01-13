package com.everywaffle.team3server.user.controller

import com.everywaffle.team3server.auth.JwtAuthenticationException
import com.everywaffle.team3server.auth.JwtTokenProvider
import com.everywaffle.team3server.user.dto.LocalSignInRequest
import com.everywaffle.team3server.user.dto.LocalSignInResponse
import com.everywaffle.team3server.user.dto.UserRequest
import com.everywaffle.team3server.user.dto.UserResponse
import com.everywaffle.team3server.user.service.*
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserSignUpController(
    private val userSignUpService: UserSignUpService,
    private val userSignInService: UserSignInService,
    private val userWithdrawalService: UserWithdrawalService,
    private val jwtTokenProvider: JwtTokenProvider
) {

    @PostMapping("/api/signin")
    fun signin(
        @RequestBody request: LocalSignInRequest,
    ): LocalSignInResponse {
        val response = userSignInService.localSignIn(request.userName, request.password)
        return response
    }

    @PostMapping("/api/signup")
    fun signup(
        @RequestBody request: UserRequest.SignUpRequest,
    ): UserResponse.SignUpResponse {
        val response = userSignUpService.signUp(request.userName, request.password, request.email)
        return response
    }

    @GetMapping("/api/withdrawal")
    fun withdrawal(
        @RequestBody withdrawalRequest: UserRequest.WithdrawalRequest,
        request: HttpServletRequest
    ) {
        val token = request.getHeader("Authorization").substring(7)
        val username = jwtTokenProvider.getAuthentication(token).principal as String

        userWithdrawalService.withdrawal(username, withdrawalRequest.password)
        // -> redirect to logout page afterward
    }

    @GetMapping("/api/signup/kakao")
    fun kakaoLogin(): String {
        return return """
            <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <title>Login</title>
                </head>
                <body>
                    <h2>Login Page</h2>
                    <div>
                        <a href="/oauth2/authorization/kakao">Login with Kakao</a>
                    </div>
                </body>
            </html>
        """.trimIndent()
    }

    @GetMapping("/welcome")
    fun home(): String {
        return "welcome"
    }

    @ExceptionHandler
    fun handleException(e: UserException): ResponseEntity<Unit> {
        val status =
            when (e) {
                is SignInUserNameNotFoundException, is SignInInvalidPasswordException, is UserNotFoundException, is WithdrawalInvalidPasswordException -> 404
                is SignUpUsernameConflictException, is SignUpEmailConflictException -> 409
            }
        return ResponseEntity.status(status).build()
    }

    @ExceptionHandler
    fun handleJwtAuthenticationException(e: JwtAuthenticationException): ResponseEntity<Unit> {
        return ResponseEntity.status(401).build()
    }

}