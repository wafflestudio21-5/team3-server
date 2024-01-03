package com.everywaffle.team3server.user.dto

import jakarta.persistence.Column

data class LocalLoginResponse(
    val userId: String,
    val userName: String,
    val email: String,
    val token: String
)
