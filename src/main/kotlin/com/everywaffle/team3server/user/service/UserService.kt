package com.everywaffle.team3server.user.service

import com.everywaffle.team3server.user.dto.UserResponse

interface UserService {
    fun signUp(userName: String, password: String, email: String): UserResponse.SignUpResponse
}