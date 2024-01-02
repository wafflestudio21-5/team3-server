package com.everywaffle.team3server.user.controller

import com.everywaffle.team3server.user.dto.UserResponse
import com.everywaffle.team3server.user.service.SignUpEmailConflictException
import com.everywaffle.team3server.user.service.SignUpUsernameConflictException
import com.everywaffle.team3server.user.service.UserException
import com.everywaffle.team3server.user.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userService: UserService
) {
    @PostMapping("/api/signup")
    fun signup(
        @RequestBody request: SignUpRequest,
    ): UserResponse.SignUpResponse {
        val response = userService.signUp(request.userName, request.password, request.email)
        return response
    }

    @ExceptionHandler
    fun handleException(e: UserException): ResponseEntity<Unit> {
        val status = when (e) {
            is SignUpUsernameConflictException, is SignUpEmailConflictException -> 409
        }
        return ResponseEntity.status(status).build()
    }
}

data class SignUpRequest(
    val userName: String,
    val password: String,
    val email: String,
)