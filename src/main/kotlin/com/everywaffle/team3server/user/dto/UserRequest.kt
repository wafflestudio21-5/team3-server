package com.everywaffle.team3server.user.dto

class UserRequest {
    data class SignUpRequest(
        val userName: String,
        val password: String,
        val email: String,
    )

    data class WithdrawalRequest(
        val password: String
    )
}