package com.everywaffle.team3server.post.service

import com.everywaffle.team3server.post.dto.VoteResponse

interface PostMakeVoteService {
    fun exists(postId: Long, userId: Long): Boolean

    fun isVotingPost(postId: Long): Boolean
    fun create(postId: Long, userId: Long): VoteResponse.MakeVoteDetail
    fun delete(postId: Long, userId: Long): VoteResponse.MakeVoteDetail
}