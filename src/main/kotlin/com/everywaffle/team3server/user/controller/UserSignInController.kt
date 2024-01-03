package com.everywaffle.team3server.user.controller

import com.everywaffle.team3server.user.dto.LocalSignInRequest
import com.everywaffle.team3server.user.dto.LocalSignInResponse
import com.everywaffle.team3server.user.service.SignInInvalidPasswordException
import com.everywaffle.team3server.user.service.SignInUserNameNotFoundException
import com.everywaffle.team3server.user.service.UserException
import com.everywaffle.team3server.user.service.UserSignInService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserSignInController (
    private val userService: UserSignInService
) {

    @PostMapping("/api/signin")
    fun signin(@RequestBody request: LocalSignInRequest): LocalSignInResponse {
        val response = userService.localSignIn(request.userName, request.password)
        return response
    }
    @ExceptionHandler
    fun handleException(e: UserException): ResponseEntity<Unit> {
        val status = when (e) {
            is SignInUserNameNotFoundException, is SignInInvalidPasswordException -> 404
        }
        return ResponseEntity.status(status).build()
    }
}