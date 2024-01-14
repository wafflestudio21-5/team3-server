package com.everywaffle.team3server.post.dto

import com.everywaffle.team3server.post.model.Category

class PostResponse {
    data class PostDetail(
        val id: Long,
        val title: String,
        val content: String,
        val category: Category,
    )
}