package com.everywaffle.team3server.post.service

import com.everywaffle.team3server.comment.repository.CommentRepository
import com.everywaffle.team3server.post.dto.PostRequest
import com.everywaffle.team3server.post.dto.PostResponse
import com.everywaffle.team3server.post.model.Category
import com.everywaffle.team3server.post.model.PostEntity
import com.everywaffle.team3server.post.repository.PostRepository
import com.everywaffle.team3server.post.repository.PostVoteRepository
import com.everywaffle.team3server.post.repository.ScrapRepository
import com.everywaffle.team3server.user.repository.UserRepository
import com.everywaffle.team3server.user.service.UserNotFoundException
import jakarta.transaction.Transactional
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class PostServiceImpl(
    private val postRepository: PostRepository,
    private val userRepository: UserRepository,
    private val scrapRepository: ScrapRepository,
    private val commentRepository: CommentRepository,
    private val postVoteRepository: PostVoteRepository
) : PostService {
    @Transactional
    override fun createPost(request: PostRequest.CreateOrUpdatePost): PostResponse.PostDetail {
        val user =
            userRepository.findById(request.userId).orElseThrow {
                UserNotFoundException()
            }

        val newPost =
            PostEntity(
                user = user,
                title = request.title,
                content = request.content,
                category = request.category,
            )
        val savedPost = postRepository.save(newPost)
        return PostResponse.PostDetail(
            id = savedPost.postId,
            userId = savedPost.user.userId,
            title = savedPost.title,
            content = savedPost.content,
            category = savedPost.category,
            createdAt = savedPost.createdAt,
            likes = savedPost.likes,
            scraps = 0,
            comments = 0,
            isVoting = false,
            agree = 0,
            disagree = 0,
        )
    }

    @Transactional
    override fun updatePost(
        postId: Long,
        request: PostRequest.CreateOrUpdatePost,
    ): PostResponse.PostDetail {
        val existingPost =
            postRepository.findById(postId).orElseThrow {
                PostNotFoundException(postId)
            }
        existingPost.apply {
            title = request.title
            content = request.content
        }
        val updatedPost = postRepository.save(existingPost)
        val scrapsCount = scrapRepository.countByPost(updatedPost)
        val commentsCount = commentRepository.countByPost(updatedPost)
        return PostResponse.PostDetail(
            id = updatedPost.postId,
            userId = updatedPost.user.userId,
            title = updatedPost.title,
            content = updatedPost.content,
            category = updatedPost.category,
            createdAt = updatedPost.createdAt,
            likes = updatedPost.likes,
            scraps = scrapsCount,
            comments = commentsCount,
            isVoting = updatedPost.makeVoteCnt >= 10,
            agree = updatedPost.agree,
            disagree = updatedPost.disagree,
        )
    }

    @Transactional
    override fun deletePost(postId: Long) {
        val post =
            postRepository.findById(postId).orElseThrow {
                PostNotFoundException(postId)
            }
        postRepository.delete(post)
    }

    override fun getPostById(postId: Long): PostResponse.PostDetail? {
        return postRepository.findById(postId).map { post ->
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
        }.orElse(null)
    }

    override fun getCategoryPost(
        category: Category,
        page: Int,
        size: Int,
    ): Page<PostResponse.PostDetail> {
        val pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"))
        return postRepository.findAllByCategory(category, pageable).map { post ->
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

    override fun getTrendingPost(
        page: Int,
        size: Int,
    ): Page<PostResponse.PostDetail> {
        val pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "likes"))
        return postRepository.findAll(pageable).map { post ->
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

    override fun getVotePost(
        page: Int,
        size: Int
    ) : Page<PostResponse.PostDetail> {
        val pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "post.createdAt"))
        return postVoteRepository.findAllVotePost(pageable).map { post ->
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

    override fun searchPost(
        keyword: String,
        category: Category?,
        page: Int,
        size: Int,
    ): Page<PostResponse.PostDetail> {
        val pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"))
        if (category == null) {
            return postRepository.findAllByTitleContainingOrContentContaining(keyword, keyword, pageable).map { post ->
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
        } else {
            return postRepository.findAllByCategoryAndTitleContainingOrContentContaining(category, keyword, keyword, pageable).map { post ->
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
}