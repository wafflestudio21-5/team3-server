package com.everywaffle.team3server.post.dto

class ScrapRequest {
    data class ScrapPost(
        val postId: Long,
        val userId: Long,
    )
}