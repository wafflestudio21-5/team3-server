package com.everywaffle.team3server.post.service

import com.everywaffle.team3server.post.dto.VoteResponse
import com.everywaffle.team3server.post.model.PostEntity
import com.everywaffle.team3server.post.model.PostLikeEntity
import com.everywaffle.team3server.post.model.PostVoteEntity
import com.everywaffle.team3server.post.model.Vote
import com.everywaffle.team3server.post.repository.PostRepository
import com.everywaffle.team3server.post.repository.PostVoteRepository
import com.everywaffle.team3server.user.repository.UserRepository
import com.everywaffle.team3server.user.service.UserNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostVoteServiceImpl(
    private val postVoteRepository: PostVoteRepository,
    private val userRepository: UserRepository,
    private val postRepository: PostRepository,
) : PostVoteService {
    override fun exists(postId: Long, userId: Long) : Boolean{
        return postVoteRepository.findByPostPostIdAndUserUserId(postId, userId) != null
    }

    @Synchronized
    override fun create(postId: Long, userId: Long, vote: Vote) : VoteResponse.VoteDetail{
        if (exists(postId, userId)) {
            throw PostVoteAlreadyExistsException(postId)
        }
        val user = userRepository.findById(userId).orElseThrow { UserNotFoundException() }
        var post = postRepository.findById(postId).orElseThrow { PostNotFoundException(postId) }
        if(post.makeVoteCnt<10) throw IsNotVotingException(postId)

        val postVote = PostVoteEntity(post = post, user = user, vote = vote)
        postVoteRepository.save(postVote)
        if(vote == Vote.AGREE){
            postRepository.incrementAgree(postId)
        }
        else{
            postRepository.incrementDisagree(postId)
        }
        val updatedPost = postRepository.findById(postId).get()
        return VoteResponse.VoteDetail(
            userId = userId,
            postId = postId,
            currentAgreeCnt = updatedPost.agree,
            currentDisagreeCnt = updatedPost.disagree,
        )
    }

    override fun delete(postId: Long, userId: Long) : VoteResponse.VoteDetail {
        val vote = postVoteRepository.findByPostPostIdAndUserUserId(postId, userId)
            ?: throw PostNeverVotedException(postId)
        postRepository.findById(postId).orElseThrow { PostNotFoundException(postId) }
        val opinion = vote.vote
        postVoteRepository.delete(vote)
        if(opinion == Vote.AGREE){
            postRepository.decrementAgree(postId)
        }
        else{
            postRepository.decrementDisagree(postId)
        }
        val updatedPost = postRepository.findById(postId).get()
        return VoteResponse.VoteDetail(
            userId = userId,
            postId = postId,
            currentAgreeCnt = updatedPost.agree,
            currentDisagreeCnt = updatedPost.disagree,
        )
    }
}