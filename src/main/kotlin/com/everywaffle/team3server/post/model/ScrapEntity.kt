package com.everywaffle.team3server.post.model

import com.everywaffle.team3server.user.model.UserEntity
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint

@Entity(name = "scraps")
@Table(
    uniqueConstraints = [
        UniqueConstraint(columnNames = ["postId", "userId"]),
    ],
)
class ScrapEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val scrapId: Long = 0,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId", referencedColumnName = "postId")
    val post: PostEntity,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    val user: UserEntity,
)