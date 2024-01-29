package com.everywaffle.team3server.post.service

import com.everywaffle.team3server.post.dto.ScrapResponse
import com.everywaffle.team3server.post.model.ScrapEntity
import com.everywaffle.team3server.post.repository.PostRepository
import com.everywaffle.team3server.post.repository.ScrapRepository
import com.everywaffle.team3server.user.repository.UserRepository
import com.everywaffle.team3server.user.service.UserNotFoundException
import org.springframework.stereotype.Service

@Service
class ScrapServiceImpl(
    private val scrapRepository: ScrapRepository,
    private val postRepository: PostRepository,
    private val userRepository: UserRepository,
) : ScrapService {
    override fun exists(postId: Long, userId: Long): Boolean {
        return scrapRepository.findByPostPostIdAndUserUserId(postId, userId) != null
    }

    override fun create(postId: Long, userId: Long): ScrapResponse.ScrapDetail {
        if (exists(postId, userId)) {
            throw ScrapAlreadyExistsException(postId)
        }
        val user = userRepository.findById(userId).orElseThrow { UserNotFoundException() }
        val post = postRepository.findById(postId).orElseThrow { PostNotFoundException(postId) }

        val scrap = ScrapEntity(post = post, user = user)
        val savedScrap = scrapRepository.save(scrap)

        return ScrapResponse.ScrapDetail(scrapId = savedScrap.scrapId, userId = userId, postId = postId)
    }

    @Synchronized
    override fun createSynchronized(postId: Long, userId: Long) {
        create(postId, userId)
    }

    override fun delete(postId: Long, userId: Long) {
        val scrap = scrapRepository.findByPostPostIdAndUserUserId(postId, userId)
            ?: throw PostNeverScrapedException(postId)

        scrapRepository.delete(scrap)
    }
}