package com.everywaffle.team3server.user.dto

class UserResponse {
    data class SignUpResponse(
        val userName: String,
        val email: String,
        val token: String,
    )
}