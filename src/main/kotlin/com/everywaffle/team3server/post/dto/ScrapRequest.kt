package com.everywaffle.team3server.post.dto

import com.everywaffle.team3server.post.model.Category

class ScrapRequest {
    data class ScrapPost(
        val postId: Long
    )
}