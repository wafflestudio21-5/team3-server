package com.everywaffle.team3server.comment.model

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

@Entity(name = "comment_likes")
@Table(
    uniqueConstraints = [
        UniqueConstraint(columnNames = ["commentId", "userId"]),
    ],
)
class CommentLikeEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val commentLikeId: Long = 0,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commentId", referencedColumnName = "commentId")
    val comment: CommentEntity,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    val user: UserEntity,
)