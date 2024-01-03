package com.everywaffle.team3server.user.service

import com.everywaffle.team3server.user.dto.LocalSignInRequest
import com.everywaffle.team3server.user.dto.LocalSignInResponse


interface UserService {

    fun localSignIn(userName: String, password: String) : LocalSignInResponse

}
