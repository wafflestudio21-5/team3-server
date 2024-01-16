package com.everywaffle.team3server.comment.dto

import java.util.Date

class CommentResponse {
    data class CommentDetail(
        val id: Long,
        val userId: Long,
        val postId: Long,
        val content: String,
        val parentCommentId: Long?,
        val createdAt: Date,
    )
}