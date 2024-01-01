package com.everywaffle.team3server.user.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

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
)