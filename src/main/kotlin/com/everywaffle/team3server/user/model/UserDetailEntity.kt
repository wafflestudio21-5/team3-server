package com.everywaffle.team3server.user.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne

@Entity(name = "user_details")
class UserDetailEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column(nullable = false)
    val realName: String,
    @Column(nullable = false)
    val nickname: String,
    @Column(nullable = false)
    val department: String,
    @Column(nullable = false)
    val studentId: Int,
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId", unique = true)
    val user: UserEntity,
)