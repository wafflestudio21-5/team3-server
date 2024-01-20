package com.everywaffle.team3server.comment.repository

import com.everywaffle.team3server.comment.model.CommentEntity
import com.everywaffle.team3server.post.model.PostEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.transaction.annotation.Transactional

interface CommentRepository : JpaRepository<CommentEntity, Long> {
    fun findByPostPostIdOrderByCreatedAt(postId: Long): List<CommentEntity>

    @Modifying
    @Transactional
    @Query(
        """
        UPDATE comments c SET c.likes = c.likes + 1 WHERE c.commentId = :commentId
    """,
    )
    fun incrementLikes(commentId: Long)

    @Modifying
    @Transactional
    @Query(
        """
        UPDATE comments c SET c.likes = c.likes - 1 WHERE c.commentId = :commentId
    """,
    )
    fun decrementLikes(commentId: Long)

    @Query("select c from comments c where c.user.userId = :userId")
    fun findByUserId(
        userId: Long,
        pageable: PageRequest,
    ): Page<CommentEntity>

    fun countByPost(post: PostEntity): Int
}