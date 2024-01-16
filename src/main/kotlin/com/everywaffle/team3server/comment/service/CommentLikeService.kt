package com.everywaffle.team3server.comment.service

interface CommentLikeService {
    fun exists(postId: Long, userId: Long): Boolean
    fun create(postId: Long, userId: Long)
    fun createSynchronized(postId: Long, userId: Long)
    fun delete(postId: Long, userId: Long)
}