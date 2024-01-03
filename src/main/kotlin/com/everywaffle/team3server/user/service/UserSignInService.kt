package com.everywaffle.team3server.user.service

import com.everywaffle.team3server.user.dto.LocalSignInResponse

interface UserSignInService {
    fun localSignIn(userName: String, password: String): LocalSignInResponse
}