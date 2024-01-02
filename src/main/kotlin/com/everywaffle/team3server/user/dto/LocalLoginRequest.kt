package com.everywaffle.team3server.user.dto

data class LocalLoginRequest(
    val id: String,
    val password: String,
)
