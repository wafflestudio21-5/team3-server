package com.everywaffle.team3server.user.controller

import com.everywaffle.team3server.user.dto.LocalSignInRequest
import com.everywaffle.team3server.user.dto.LocalSignInResponse
import com.everywaffle.team3server.user.service.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody


class UserController (
    private val userService: UserService
) {

    @PostMapping("/api/signin")
    fun signin(@RequestBody request: LocalSignInRequest): LocalSignInResponse {
        val response = userService.localSignIn(request.userName, request.password)
        return response
    }

}