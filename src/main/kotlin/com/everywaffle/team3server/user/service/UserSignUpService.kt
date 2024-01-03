package com.everywaffle.team3server.user.service

import com.everywaffle.team3server.user.dto.UserResponse

interface UserSignUpService {
    fun signUp(userName: String, password: String, email: String): UserResponse.SignUpResponse
}