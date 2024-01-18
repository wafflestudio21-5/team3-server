package com.everywaffle.team3server.post.service

import com.everywaffle.team3server.post.dto.ScrapResponse

interface ScrapService {
    fun exists(postId: Long, userId: Long): Boolean
    fun create(postId: Long, userId: Long): ScrapResponse.ScrapDetail
    fun createSynchronized(postId: Long, userId: Long)
    fun delete(postId: Long, userId: Long)
}