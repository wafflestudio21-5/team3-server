package com.everywaffle.team3server.post.dto

import com.everywaffle.team3server.post.model.Category

class PostRequest {
    data class CreateOrUpdatePost(
        val userId: Long,
        val title: String,
        val content: String,
        val category: Category,
    )
    data class CreateOrUpdatePostLike(
        val userId: Long,
    )
}