package com.everywaffle.team3server.user.service

import com.everywaffle.team3server.user.dto.LocalLoginRequest
import com.everywaffle.team3server.user.dto.LocalLoginResponse


interface UserService {
    fun localLogin(localLoginRequest: LocalLoginRequest): LocalLoginResponse?
    fun authenticate(username: String, password: String)
    // TODO: 다른 메서드 정의
}
