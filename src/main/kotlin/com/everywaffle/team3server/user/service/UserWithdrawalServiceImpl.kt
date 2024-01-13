package com.everywaffle.team3server.user.service

import com.everywaffle.team3server.auth.JwtAuthenticationException
import com.everywaffle.team3server.user.model.UserEntity
import com.everywaffle.team3server.user.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserWithdrawalServiceImpl(
    private val userRepository: UserRepository
): UserWithdrawalService {

    override fun withdrawal(userName: String, password: String) {
        val user: UserEntity = userRepository.findByUserName(userName)
            ?: throw UserNotFoundException()

        if (user.password != password) {
            throw WithdrawalInvalidPasswordException()
        }
        userRepository.delete(user)
    }
}