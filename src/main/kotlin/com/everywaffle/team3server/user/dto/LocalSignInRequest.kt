package com.everywaffle.team3server.user.dto

data class LocalSignInRequest(
    val userName: String,
    val password: String,
)