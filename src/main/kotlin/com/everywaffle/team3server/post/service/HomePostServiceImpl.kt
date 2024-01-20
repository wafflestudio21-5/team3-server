package com.everywaffle.team3server.post.service

import com.everywaffle.team3server.comment.repository.CommentRepository
import com.everywaffle.team3server.post.dto.PostResponse
import com.everywaffle.team3server.post.model.Category
import com.everywaffle.team3server.post.repository.PostRepository
import com.everywaffle.team3server.post.repository.ScrapRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class HomePostServiceImpl(
    private val postRepository: PostRepository,
    private val scrapRepository: ScrapRepository,
    private val commentRepository: CommentRepository,
) : HomePostService {
    @Transactional
    override fun getLatestPostByCategory(category: Category): PostResponse.PostDetail? {
        return postRepository.findTopByCategoryOrderByCreatedAtDesc(category)?.let { post ->
            val scrapsCount = scrapRepository.countByPost(post)
            val commentsCount = commentRepository.countByPost(post)
            PostResponse.PostDetail(
                id = post.postId,
                userId = post.user.userId,
                title = post.title,
                content = post.content,
                category = post.category,
                createdAt = post.createdAt,
                likes = post.likes,
                scraps = scrapsCount,
                comments = commentsCount,
            )
        }
    }

    @Transactional
    override fun getTrendingPosts(): List<PostResponse.PostDetail> {
        return postRepository.findTop2ByLikesGreaterThanOrderByCreatedAtDesc(10).map { post ->
            val scrapsCount = scrapRepository.countByPost(post)
            val commentsCount = commentRepository.countByPost(post)
            PostResponse.PostDetail(
                id = post.postId,
                userId = post.user.userId,
                title = post.title,
                content = post.content,
                category = post.category,
                createdAt = post.createdAt,
                likes = post.likes,
                scraps = scrapsCount,
                comments = commentsCount,
            )
        }
    }
}