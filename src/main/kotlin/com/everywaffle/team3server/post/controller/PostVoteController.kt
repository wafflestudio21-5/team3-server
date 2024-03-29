package com.everywaffle.team3server.post.controller

import com.everywaffle.team3server.post.dto.VoteRequest
import com.everywaffle.team3server.post.dto.VoteResponse
import com.everywaffle.team3server.post.service.AlreadyVotingPostException
import com.everywaffle.team3server.post.service.IsNotVotingException
import com.everywaffle.team3server.post.service.PostException
import com.everywaffle.team3server.post.service.PostMakeVoteAlreadyExistsException
import com.everywaffle.team3server.post.service.PostMakeVoteService
import com.everywaffle.team3server.post.service.PostNeverVotedException
import com.everywaffle.team3server.post.service.PostVoteAlreadyExistsException
import com.everywaffle.team3server.post.service.PostVoteService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/post")
class PostVoteController(
    private val postVoteService: PostVoteService,
    private val postMakeVoteService: PostMakeVoteService,
) {
    @PostMapping("/{postId}/makeVote")
    fun createMakeVote(
        @PathVariable postId: Long,
        @RequestBody request: VoteRequest.CreatePostMakeVote,
    ): ResponseEntity<VoteResponse.MakeVoteDetail> {
        val makeVoteDetail = postMakeVoteService.create(postId = postId, userId = request.userId)
        return ResponseEntity.ok(makeVoteDetail)
    }

    @DeleteMapping("/{postId}/makeVote")
    fun deleteMakeVote(
        @PathVariable postId: Long,
        @RequestBody request: VoteRequest.DeletePostMakeVote,
    ): ResponseEntity<VoteResponse.MakeVoteDetail> {
        val makeVoteDetail = postMakeVoteService.delete(postId = postId, userId = request.userId)
        return ResponseEntity.ok(makeVoteDetail)
    }

    @PostMapping("/{postId}/vote")
    fun createVote(
        @PathVariable postId: Long,
        @RequestBody request: VoteRequest.CreatePostVote,
    ): ResponseEntity<VoteResponse.VoteDetail> {
        val voteDetail = postVoteService.create(postId = postId, userId = request.userId, vote = request.vote)
        return ResponseEntity.ok(voteDetail)
    }

    @DeleteMapping("/{postId}/vote")
    fun deleteVote(
        @PathVariable postId: Long,
        @RequestBody request: VoteRequest.DeletePostVote,
    ): ResponseEntity<VoteResponse.VoteDetail> {
        val voteDetail = postVoteService.delete(postId = postId, userId = request.userId)
        return ResponseEntity.ok(voteDetail)
    }

    @ExceptionHandler
    fun handlePostVoteException(e: PostException): ResponseEntity<Unit> {
        val status =
            when (e) {
                is PostVoteAlreadyExistsException, is PostMakeVoteAlreadyExistsException, is AlreadyVotingPostException -> 409
                is IsNotVotingException -> 403
                is PostNeverVotedException -> 404
                else -> 404
            }
        return ResponseEntity.status(status).build()
    }
}