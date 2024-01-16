package com.everywaffle.team3server.post.service

import com.everywaffle.team3server.post.dto.PostRequest
import com.everywaffle.team3server.post.dto.PostResponse
import com.everywaffle.team3server.post.model.Category
import org.springframework.data.domain.Page
import java.awt.print.Pageable

interface PostService {
    fun createPost(request: PostRequest.CreateOrUpdatePost): PostResponse.PostDetail

    fun updatePost(
        postId: Long,
        request: PostRequest.CreateOrUpdatePost,
    ): PostResponse.PostDetail

    fun deletePost(postId: Long)

    fun getPostById(postId: Long): PostResponse.PostDetail?

    fun getCategoryPost(category: Category, page: Int, size: Int): Page<PostResponse.PostDetail>

    fun getTrendingPost(page: Int, size: Int): Page<PostResponse.PostDetail>
}