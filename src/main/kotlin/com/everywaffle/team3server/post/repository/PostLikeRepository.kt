package com.everywaffle.team3server.post.repository

import com.everywaffle.team3server.post.model.PostLikeEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PostLikeRepository : JpaRepository<PostLikeEntity, Long> {
    fun findByPostPostIdAndUserUserId(postId: Long, userId: Long): PostLikeEntity?
}