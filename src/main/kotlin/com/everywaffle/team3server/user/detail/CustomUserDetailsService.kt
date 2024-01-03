package com.everywaffle.team3server.user.detail

import com.everywaffle.team3server.user.model.UserEntity
import com.everywaffle.team3server.user.repository.UserSignInRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException

class CustomUserDetailsService(
    private val userSignInRepository: UserSignInRepository
) : UserDetailsService {
    override fun loadUserByUsername(userName: String): UserDetails {
        val user: UserEntity = userSignInRepository.findByUserName(userName)
            ?: throw UsernameNotFoundException("UserName Not Found.")
        return CustomUserDetails(user)
    }

}