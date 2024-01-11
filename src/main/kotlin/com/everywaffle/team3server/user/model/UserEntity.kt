package com.everywaffle.team3server.user.model

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToOne

@Entity(name = "users")
class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val userId: Long = 0,
    @Column(unique = true)
    val userName: String,
    val password: String,
    @Column(unique = true)
    val email: String,
    @OneToOne(mappedBy = "user_details_id", cascade = [CascadeType.ALL])
    val userDetail: UserDetailEntity? = null,
)