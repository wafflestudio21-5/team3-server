package com.everywaffle.team3server.user.dto

class UserResponse {
    data class SignUpResponse(
        val userId: Long,
        val userName: String,
        val email: String,
        val token: String,
    )
}