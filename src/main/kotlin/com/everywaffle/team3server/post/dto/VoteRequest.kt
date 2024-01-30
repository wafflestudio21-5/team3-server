package com.everywaffle.team3server.post.dto

import com.everywaffle.team3server.post.model.Vote

class VoteRequest {
    data class CreatePostMakeVote(
        val userId: Long,
    )

    data class DeletePostMakeVote(
        val userId: Long,
    )
    data class CreatePostVote(
        val userId: Long,
        val vote: Vote,
    )

    data class DeletePostVote(
        val userId: Long,
    )
}