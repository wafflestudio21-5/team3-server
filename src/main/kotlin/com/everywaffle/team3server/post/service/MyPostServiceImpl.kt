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
    private val commentRepository: CommentRepository
) : MyPostService {

    override fun getMyPosts(userId: Long, page: Int, size: Int): Page<PostResponse.PostDetail> {
        val pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"))
        return postRepository.findByUserId(userId, pageable).map { post ->
            PostResponse.PostDetail(
                id = post.postId,
                userId = post.user.userId,
                title = post.title,
                content = post.content,
                category = post.category,
                createdAt = post.createdAt,
                likes = post.likes
            )
        }
    }

    override fun getMyScrappedPosts(userId: Long, page: Int, size: Int): Page<PostResponse.PostDetail> {
        val pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"))
        return scrapRepository.findByUserId(userId, pageable).map { scrap ->
            PostResponse.PostDetail(
                id = scrap.post.postId,
                userId = scrap.user.userId,
                title = scrap.post.title,
                content = scrap.post.content,
                category = scrap.post.category,
                createdAt = scrap.post.createdAt,
                likes = scrap.post.likes
            )
        }
    }

    override fun getMyCommentedPosts(userId: Long, page: Int, size: Int): Page<PostResponse.PostDetail> {
        val pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"))
        return commentRepository.findByUserId(userId, pageable).map { comment ->
            PostResponse.PostDetail(
                id = comment.post.postId,
                userId = comment.user.userId,
                title = comment.post.title,
                content = comment.post.content,
                category = comment.post.category,
                createdAt = comment.post.createdAt,
                likes = comment.post.likes
            )
        }
    }
}