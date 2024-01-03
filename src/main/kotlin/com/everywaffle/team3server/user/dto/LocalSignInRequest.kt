package com.everywaffle.team3server.user.dto

data class LocalLoginRequest(
    val username: String,
    val password: String,
)
