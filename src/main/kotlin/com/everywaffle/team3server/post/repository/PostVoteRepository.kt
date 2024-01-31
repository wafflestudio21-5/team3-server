package com.everywaffle.team3server.post.repository

import com.everywaffle.team3server.post.model.PostEntity
import com.everywaffle.team3server.post.model.PostVoteEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface PostVoteRepository : JpaRepository<PostVoteEntity, Long> {
    fun findByPostPostIdAndUserUserId(postId: Long, userId: Long): PostVoteEntity?

    @Query("select distinct pv.post from post_votes pv")
    fun findAllVotePost(
        pageable: PageRequest
    ): Page<PostEntity>
}