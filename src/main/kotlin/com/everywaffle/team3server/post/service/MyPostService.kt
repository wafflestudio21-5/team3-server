package com.everywaffle.team3server.post.service

import com.everywaffle.team3server.post.dto.PostResponse
import org.springframework.data.domain.Page

interface MyPostService {

    fun getMyPosts(userId: Long, page: Int, size: Int): Page<PostResponse.PostDetail>

    fun getMyScrappedPosts(userId: Long, page: Int, size: Int): Page<PostResponse.PostDetail>

    fun getMyCommentedPosts(userId: Long, page: Int, size: Int): Page<PostResponse.PostDetail>
}