package com.everywaffle.team3server.comment.service

import com.everywaffle.team3server.comment.dto.CommentRequest
import com.everywaffle.team3server.comment.dto.CommentResponse

interface CommentService {
    fun createComment(request: CommentRequest.CreateOrUpdateComment): CommentResponse.CommentDetail

    fun updateComment(
        commentId: Long,
        request: CommentRequest.CreateOrUpdateComment,
    ): CommentResponse.CommentDetail

    fun deleteComment(commentId: Long)

    fun getCommentsByPostId(postId: Long): List<CommentResponse.CommentDetailList>
}