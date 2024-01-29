package com.everywaffle.team3server.post.controller

import com.everywaffle.team3server.post.dto.PostRequest
import com.everywaffle.team3server.post.service.LikeAlreadyExistsException
import com.everywaffle.team3server.post.service.PostLikeService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
@RestController
@RequestMapping("/api/post")
class PostLikeController(private val postLikeService: PostLikeService) {
    @PostMapping("/{postId}/likes")
    fun likePost(
        @PathVariable postId: Long,
        @RequestBody request: PostRequest.CreateOrUpdatePostLike
    ): ResponseEntity<Void> {
        postLikeService.create(postId, request.userId)
        return ResponseEntity.ok().build()
    }
    @ExceptionHandler
    fun handleLikeAlreadyExistsException(e: LikeAlreadyExistsException): ResponseEntity<Unit> {
        val status =
            when (e) {
                is LikeAlreadyExistsException -> 409
                else -> 404
            }
        return ResponseEntity.status(status).build()
    }
}