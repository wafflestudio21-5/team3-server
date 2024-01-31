package com.everywaffle.team3server.post.dto

import com.everywaffle.team3server.post.model.Category
import java.util.Date

class PostResponse {
    data class PostDetail(
        val id: Long,
        val userId: Long,
        val title: String,
        val content: String,
        val category: Category,
        val createdAt: Date,
        val likes: Int,
        val scraps: Int,
        val comments: Int,
        val isVoting: Boolean,
        val agree: Int,
        val disagree: Int,
    )
}