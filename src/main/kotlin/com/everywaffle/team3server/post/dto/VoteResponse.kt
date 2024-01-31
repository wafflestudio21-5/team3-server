package com.everywaffle.team3server.post.dto

class VoteResponse {
    data class MakeVoteDetail(
        val userId: Long,
        val postId: Long,
        val currentMakeVoteCnt: Int,
    )

    data class VoteDetail(
        val userId: Long,
        val postId: Long,
        val currentAgreeCnt: Int,
        val currentDisagreeCnt: Int,
    )
}