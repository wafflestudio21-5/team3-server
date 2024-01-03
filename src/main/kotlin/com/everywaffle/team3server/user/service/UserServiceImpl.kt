package com.everywaffle.team3server.user.service

import com.everywaffle.team3server.auth.JwtTokenProvider
import com.everywaffle.team3server.user.dto.LocalSignInRequest
import com.everywaffle.team3server.user.dto.LocalSignInResponse
import com.everywaffle.team3server.user.dto.UserResponse
import com.everywaffle.team3server.user.model.UserEntity
import com.everywaffle.team3server.user.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val jwtTokenProvider: JwtTokenProvider
) : UserService {
    override fun localSignIn(userName: String, password: String): LocalSignInResponse {
        val user: UserEntity = userRepository.findByUserName(userName)
            ?: throw SignInUserNameNotFoundException()

        if (user.password != password) throw SignInInvalidPasswordException()

        return LocalSignInResponse(
            userId = user.userId,
            userName = user.userName,
            email = user.email,
            token = jwtTokenProvider.createToken(user.userName)
        )
    }
}
