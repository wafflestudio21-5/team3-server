package com.everywaffle.team3server.user.dto

data class LocalSignInResponse(
    val userId: Long,
    val userName: String,
    val email: String,
    val token: String
)
