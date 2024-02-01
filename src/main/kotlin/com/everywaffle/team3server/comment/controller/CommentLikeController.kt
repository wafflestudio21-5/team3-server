package com.everywaffle.team3server.comment.controller

import com.everywaffle.team3server.comment.dto.CommentRequest
import com.everywaffle.team3server.comment.service.CommentLikeService
import com.everywaffle.team3server.comment.service.LikeAlreadyExistsException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/comment")
class CommentLikeController(private val commentLikeService: CommentLikeService) {
    @PostMapping("/{commentId}/likes")
    fun likePost(
        @PathVariable commentId: Long,
        @RequestBody request: CommentRequest.CreateOrUpdateCommentLike
    ): ResponseEntity<Void> {
        commentLikeService.create(commentId, request.userId)
        return ResponseEntity.ok().build()
    }
    @ExceptionHandler
    fun handleLikeAlreadyExistsException(e: LikeAlreadyExistsException): ResponseEntity<String> {
        return ResponseEntity.status(409).build()
    }
}