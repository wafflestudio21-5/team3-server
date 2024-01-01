package com.everywaffle.team3server.post.model

import com.everywaffle.team3server.user.model.UserEntity
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Temporal
import jakarta.persistence.TemporalType
import java.util.Date

@Entity(name = "posts")
class PostEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val postId: Long = 0,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    val user: UserEntity,
    val title: String,
    val content: String,
    @Enumerated(EnumType.STRING)
    val category: Category,
    @Temporal(TemporalType.TIMESTAMP)
    val createdAt: Date = Date(),
    val likes: Int = 0,
)