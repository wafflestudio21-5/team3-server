package com.everywaffle.team3server.post.service

import com.everywaffle.team3server.comment.repository.CommentRepository
import com.everywaffle.team3server.post.dto.PostResponse
import com.everywaffle.team3server.post.repository.PostRepository
import com.everywaffle.team3server.post.repository.ScrapRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class MyPostServiceImpl(
    private val scrapRepository: ScrapRepository,
    private val postRepository: PostRepository,
    private val commentRepository: CommentRepository,
) : MyPostService {
    override fun getMyPosts(
        userId: Long,
        page: Int,
        size: Int,
    ): Page<PostResponse.PostDetail> {
        val pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"))
        return postRepository.findByUserId(userId, pageable).map { post ->
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
                isVoting = post.makeVoteCnt >= 10,
                agree = post.agree,
                disagree = post.disagree,
            )
        }
    }

    override fun getMyScrappedPosts(
        userId: Long,
        page: Int,
        size: Int,
    ): Page<PostResponse.PostDetail> {
        val pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "post.createdAt"))
        return scrapRepository.findScrappedPostByUserId(userId, pageable).map { post ->
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
                isVoting = post.makeVoteCnt >= 10,
                agree = post.agree,
                disagree = post.disagree,
            )
        }
    }

    override fun getMyCommentedPosts(
        userId: Long,
        page: Int,
        size: Int,
    ): Page<PostResponse.PostDetail> {
        val pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "post.createdAt"))
        return commentRepository.findDistinctCommentedPostByUserId(userId, pageable).map { post ->
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
                isVoting = post.makeVoteCnt >= 10,
                agree = post.agree,
                disagree = post.disagree,
            )
        }
    }
}