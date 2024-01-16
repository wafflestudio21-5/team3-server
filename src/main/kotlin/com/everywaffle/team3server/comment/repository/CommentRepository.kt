package com.everywaffle.team3server.comment.repository

import com.everywaffle.team3server.comment.model.CommentEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface CommentRepository : JpaRepository<CommentEntity, Long> {
    fun findByPostPostIdOrderByCreatedAt(postId: Long): List<CommentEntity>

    @Query("select c from comments c where c.user.userId = :userId")
    fun findByUserId(userId: Long, pageable: PageRequest): Page<CommentEntity>
}