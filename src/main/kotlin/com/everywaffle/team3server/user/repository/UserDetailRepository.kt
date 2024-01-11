package com.everywaffle.team3server.user.repository

import com.everywaffle.team3server.user.model.UserDetailEntity
import com.everywaffle.team3server.user.model.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserDetailRepository : JpaRepository<UserDetailEntity, Long>{
    fun findByUser(user: UserEntity) : UserDetailEntity?
}