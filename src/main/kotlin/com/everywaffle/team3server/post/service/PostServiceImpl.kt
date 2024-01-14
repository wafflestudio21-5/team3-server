package com.everywaffle.team3server.post.service

import com.everywaffle.team3server.post.dto.PostRequest
import com.everywaffle.team3server.post.dto.PostResponse
import com.everywaffle.team3server.post.model.PostEntity
import com.everywaffle.team3server.post.repository.PostRepository
import com.everywaffle.team3server.user.repository.UserRepository
import com.everywaffle.team3server.user.service.UserNotFoundException
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class PostServiceImpl(
    private val postRepository: PostRepository,
    private val userRepository: UserRepository,
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
            title = savedPost.title,
            content = savedPost.content,
            category = savedPost.category,
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
        return PostResponse.PostDetail(
            id = updatedPost.postId,
            title = updatedPost.title,
            content = updatedPost.content,
            category = updatedPost.category,
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
            PostResponse.PostDetail(
                id = post.postId,
                title = post.title,
                content = post.content,
                category = post.category,
            )
        }.orElse(null)
    }
}