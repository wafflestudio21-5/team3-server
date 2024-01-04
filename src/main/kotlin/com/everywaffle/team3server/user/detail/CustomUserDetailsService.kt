package com.everywaffle.team3server.user.detail

import com.everywaffle.team3server.user.model.UserEntity
import com.everywaffle.team3server.user.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException

class CustomUserDetailsService(
    private val userRepository: UserRepository
) : UserDetailsService {
    override fun loadUserByUsername(userName: String): UserDetails {
        val user: UserEntity = userRepository.findByUserName(userName)
            ?: throw UsernameNotFoundException("UserName Not Found.")
        return CustomUserDetails(user)
    }
}