package com.everywaffle.team3server.comment.repository

import com.everywaffle.team3server.comment.model.CommentEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<CommentEntity, Long> {
    fun findByPostPostIdOrderByCreatedAt(postId: Long): List<CommentEntity>
}