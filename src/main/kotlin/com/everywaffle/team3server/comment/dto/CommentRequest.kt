package com.everywaffle.team3server.comment.dto

class CommentRequest {
    data class CreateOrUpdateComment(
        val userId: Long,
        val postId: Long,
        val content: String,
        val parentCommentId: Long? = null,
        val likes: Int,
    )
}