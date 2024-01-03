package com.everywaffle.team3server.user.repository

import com.everywaffle.team3server.user.model.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserSignInRepository : JpaRepository<UserEntity, Long> {
    fun findByUserName(userName: String): UserEntity?
    fun findByEmail(email: String): UserEntity?
}