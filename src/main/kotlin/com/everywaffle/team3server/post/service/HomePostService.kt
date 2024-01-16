package com.everywaffle.team3server.post.service

import com.everywaffle.team3server.post.dto.PostResponse
import com.everywaffle.team3server.post.model.Category

interface HomePostService {
    fun getLatestPostByCategory(category: Category): PostResponse.PostDetail?

    fun getTrendingPosts(): List<PostResponse.PostDetail>
}