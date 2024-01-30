package com.everywaffle.team3server.post.repository

import com.everywaffle.team3server.post.model.PostVoteEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PostVoteRepository : JpaRepository<PostVoteEntity, Long> {
    fun findByPostPostIdAndUserUserId(postId: Long, userId: Long): PostVoteEntity?
}