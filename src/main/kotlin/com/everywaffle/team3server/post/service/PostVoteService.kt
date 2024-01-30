package com.everywaffle.team3server.post.service

import com.everywaffle.team3server.post.dto.VoteResponse
import com.everywaffle.team3server.post.model.Vote

interface PostVoteService {
    fun exists(postId: Long, userId: Long): Boolean
    fun create(postId: Long, userId: Long, vote: Vote): VoteResponse.VoteDetail
    fun delete(postId: Long, userId: Long): VoteResponse.VoteDetail
}