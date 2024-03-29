package com.everywaffle.team3server.user.service

import com.everywaffle.team3server.auth.JwtTokenProvider
import com.everywaffle.team3server.user.dto.UserResponse
import com.everywaffle.team3server.user.model.UserEntity
import com.everywaffle.team3server.user.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserSignUpServiceImpl(
    private val userRepository: UserRepository,
    private val jwtTokenProvider: JwtTokenProvider,
) : UserSignUpService {
    override fun signUp(userName: String, password: String, email: String): UserResponse.SignUpResponse {
        if (userRepository.findByUserName(userName) != null) {
            throw SignUpUsernameConflictException()
        }
        if (userRepository.findByEmail(email) != null) {
            throw SignUpEmailConflictException()
        }
        val user = userRepository.save(
            UserEntity(
                userName = userName,
                password = password,
                email = email,
            )
        )
        val token = jwtTokenProvider.createToken(userName)
        return UserResponse.SignUpResponse(user.userId, userName, email, token)
    }
}