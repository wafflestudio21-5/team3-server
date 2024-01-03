package com.everywaffle.team3server.user.controller

import com.everywaffle.team3server.user.dto.UserRequest
import com.everywaffle.team3server.user.dto.UserResponse
import com.everywaffle.team3server.user.service.SignUpEmailConflictException
import com.everywaffle.team3server.user.service.SignUpUsernameConflictException
import com.everywaffle.team3server.user.service.UserException
import com.everywaffle.team3server.user.service.UserSignUpService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserSignUpController(
    private val userSignUpService: UserSignUpService
) {
    @PostMapping("/api/signup")
    fun signup(
        @RequestBody request: UserRequest.SignUpRequest,
    ): UserResponse.SignUpResponse {
        val response = userSignUpService.signUp(request.userName, request.password, request.email)
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