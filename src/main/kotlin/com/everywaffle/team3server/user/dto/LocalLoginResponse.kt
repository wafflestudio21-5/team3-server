package com.everywaffle.team3server.user.dto

data class LocalLoginResponse(
    val userId: String,
    val token: String,
    val message: String = "ok",
)
