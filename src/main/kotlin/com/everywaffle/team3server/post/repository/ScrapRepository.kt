package com.everywaffle.team3server.post.repository

import com.everywaffle.team3server.post.model.PostLikeEntity
import com.everywaffle.team3server.post.model.ScrapEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ScrapRepository : JpaRepository<ScrapEntity, Long> {
    fun findByPostPostIdAndUserUserId(postId: Long, userId: Long): ScrapEntity?
}