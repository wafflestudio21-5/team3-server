package com.everywaffle.team3server.user.service

import com.everywaffle.team3server.auth.JwtTokenProvider
import com.everywaffle.team3server.user.dto.LocalSignInResponse
import com.everywaffle.team3server.user.model.UserEntity
import com.everywaffle.team3server.user.repository.UserSignInRepository
import org.springframework.stereotype.Service

@Service
class UserSignInServiceImpl(
    private val userSignInRepository: UserSignInRepository,
    private val jwtTokenProvider: JwtTokenProvider
) : UserSignInService {
    override fun localSignIn(userName: String, password: String): LocalSignInResponse {
        val user: UserEntity = userSignInRepository.findByUserName(userName)
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
