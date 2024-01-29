package com.everywaffle.team3server.post.service

import com.everywaffle.team3server.post.model.PostLikeEntity
import com.everywaffle.team3server.post.repository.PostLikeRepository
import com.everywaffle.team3server.post.repository.PostRepository
import com.everywaffle.team3server.user.repository.UserRepository
import com.everywaffle.team3server.user.service.UserNotFoundException
import org.springframework.stereotype.Service

@Service
class PostLikeServiceImpl(
    private val postLikeRepository: PostLikeRepository,
    private val postRepository: PostRepository,
    private val userRepository: UserRepository,
) : PostLikeService {
    override fun exists(postId: Long, userId: Long): Boolean {
        return postLikeRepository.findByPostPostIdAndUserUserId(postId, userId) != null
    }

    override fun create(postId: Long, userId: Long) {
        if (exists(postId, userId)) {
            throw LikeAlreadyExistsException(postId)
        }
        val user = userRepository.findById(userId).orElseThrow { UserNotFoundException() }
        val post = postRepository.findById(postId).orElseThrow { PostNotFoundException(postId) }

        val postLike = PostLikeEntity(post = post, user = user)
        postLikeRepository.save(postLike)

        postRepository.incrementLikes(postId)
    }

    @Synchronized
    override fun createSynchronized(postId: Long, userId: Long) {
        create(postId, userId)
    }

    override fun delete(postId: Long, userId: Long) {
        val like = postLikeRepository.findByPostPostIdAndUserUserId(postId, userId)
            ?: throw PostNeverLikedException(postId)
        val post = postRepository.findById(postId).orElseThrow { PostNotFoundException(postId) }

        postLikeRepository.delete(like)

        postRepository.decrementLikes(postId)
    }
}