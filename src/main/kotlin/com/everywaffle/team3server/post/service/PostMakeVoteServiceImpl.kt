package com.everywaffle.team3server.post.service

import com.everywaffle.team3server.post.dto.VoteResponse
import com.everywaffle.team3server.post.model.PostMakeVoteEntity
import com.everywaffle.team3server.post.repository.PostMakeVoteRepository
import com.everywaffle.team3server.post.repository.PostRepository
import com.everywaffle.team3server.user.repository.UserRepository
import com.everywaffle.team3server.user.service.UserNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostMakeVoteServiceImpl(
    private val postMakeVoteRepository: PostMakeVoteRepository,
    private val postRepository: PostRepository,
    private val userRepository: UserRepository,
) : PostMakeVoteService{
    override fun exists(postId: Long, userId: Long): Boolean {
        return postMakeVoteRepository.findByPostPostIdAndUserUserId(postId, userId) != null
    }

    override fun isVotingPost(postId: Long): Boolean {
        val post = postRepository.findById(postId).orElseThrow{ PostNotFoundException(postId) }
        return post.makeVoteCnt >= 10
    }

    @Synchronized
    override fun create(postId: Long, userId: Long): VoteResponse.MakeVoteDetail {
        if (exists(postId, userId)) {
            throw PostMakeVoteAlreadyExistsException(postId)
        }
        val user = userRepository.findById(userId).orElseThrow { UserNotFoundException() }
        val post = postRepository.findById(postId).orElseThrow { PostNotFoundException(postId) }
        if(isVotingPost(postId)){
            throw AlreadyVotingPostException(postId)
        }
        val postMakeVote = PostMakeVoteEntity(post = post, user = user)
        postMakeVoteRepository.save(postMakeVote)
        postRepository.incrementMakeVoteCnt(postId)
        return VoteResponse.MakeVoteDetail(
            userId = userId,
            postId = postId,
            currentMakeVoteCnt = postRepository.findById(postId).get().makeVoteCnt,
        )
    }

    override fun delete(postId: Long, userId: Long) : VoteResponse.MakeVoteDetail {
        val vote = postMakeVoteRepository.findByPostPostIdAndUserUserId(postId, userId)
            ?: throw PostNeverVotedException(postId)
        postRepository.findById(postId).orElseThrow { PostNotFoundException(postId) }
        postMakeVoteRepository.delete(vote)
        postRepository.decrementMakeVoteCnt(postId)
        return VoteResponse.MakeVoteDetail(
            userId = userId,
            postId = postId,
            currentMakeVoteCnt = postRepository.findById(postId).get().makeVoteCnt,
        )
    }
}