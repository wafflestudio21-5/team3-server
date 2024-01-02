package com.everywaffle.team3server.user.service

import com.everywaffle.team3server.auth.JwtTokenProvider
import com.everywaffle.team3server.user.dto.LocalLoginRequest
import com.everywaffle.team3server.user.dto.LocalLoginResponse
import com.everywaffle.team3server.user.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtTokenProvider: JwtTokenProvider
) : UserService{
    override fun localLogin(localLoginRequest: LocalLoginRequest): LocalLoginResponse? {
        TODO("Not yet implemented")
    }
    override fun authenticate(username: String, password: String) {
        TODO("Not yet implemented")
    }
}