package com.everywaffle.team3server.comment.service

interface CommentLikeService {
    fun exists(commentId: Long, userId: Long): Boolean
    fun create(commentId: Long, userId: Long)
    fun createSynchronized(commentId: Long, userId: Long)
    fun delete(commentId: Long, userId: Long)
}