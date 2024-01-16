package com.everywaffle.team3server.post.service

import com.everywaffle.team3server.post.dto.PostResponse
import com.everywaffle.team3server.post.model.Category
import com.everywaffle.team3server.post.repository.PostRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class HomePostServiceImpl(private val postRepository: PostRepository) : HomePostService {
    @Transactional
    override fun getLatestPostByCategory(category: Category): PostResponse.PostDetail? {
        return postRepository.findTopByCategoryOrderByCreatedAtDesc(category)?.let {
            PostResponse.PostDetail(
                id = it.postId,
                userId = it.user.userId,
                title = it.title,
                content = it.content,
                category = it.category,
                createdAt = it.createdAt,
                likes = it.likes,
            )
        }
    }

    @Transactional
    override fun getTrendingPosts(): List<PostResponse.PostDetail> {
        return postRepository.findTop2ByLikesGreaterThanOrderByCreatedAtDesc(10).map {
            PostResponse.PostDetail(
                id = it.postId,
                userId = it.user.userId,
                title = it.title,
                content = it.content,
                category = it.category,
                createdAt = it.createdAt,
                likes = it.likes,
            )
        }
    }
}