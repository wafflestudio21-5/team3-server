package com.everywaffle.team3server.post.repository

import com.everywaffle.team3server.user.repository.UserEntity
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint

@Entity(name = "post_likes")
@Table(
    uniqueConstraints = [
        UniqueConstraint(columnNames = ["postId", "userId"]),
    ],
)
class PostLikeEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val postLikeId: Long = 0,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId", referencedColumnName = "postId")
    val post: PostEntity,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    val user: UserEntity,
)