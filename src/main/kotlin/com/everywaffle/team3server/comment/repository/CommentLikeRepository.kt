package com.everywaffle.team3server.comment.repository

import com.everywaffle.team3server.comment.model.CommentLikeEntity
import com.everywaffle.team3server.post.model.PostLikeEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CommentLikeRepository : JpaRepository<CommentLikeEntity, Long> {
    fun findByCommentCommentIdAndUserUserId(commentId: Long, userId: Long): CommentLikeEntity?

}