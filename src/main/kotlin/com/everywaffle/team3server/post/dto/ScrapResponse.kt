package com.everywaffle.team3server.post.dto

class ScrapResponse {
    data class ScrapDetail(
        val scrapId: Long,
        val userId: Long,
        val postId: Long
    )
}