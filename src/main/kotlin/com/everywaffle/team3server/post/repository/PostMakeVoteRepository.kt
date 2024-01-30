package com.everywaffle.team3server.post.repository

import com.everywaffle.team3server.post.model.PostMakeVoteEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PostMakeVoteRepository : JpaRepository<PostMakeVoteEntity, Long> {
    fun findByPostPostIdAndUserUserId(postId: Long, userId: Long): PostMakeVoteEntity?
}