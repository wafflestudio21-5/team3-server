package com.everywaffle.team3server.comment.controller

import com.everywaffle.team3server.comment.dto.CommentRequest
import com.everywaffle.team3server.comment.dto.CommentResponse
import com.everywaffle.team3server.comment.service.CommentNotFoundException
import com.everywaffle.team3server.comment.service.CommentService
import com.everywaffle.team3server.comment.service.LikeAlreadyExistsException
import com.everywaffle.team3server.post.service.PostNotFoundException
import com.everywaffle.team3server.user.service.UserNotFoundException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/comment")
class CommentController(private val commentService: CommentService) {
    @PostMapping
    fun createComment(
        @RequestBody request: CommentRequest.CreateOrUpdateComment,
    ): ResponseEntity<CommentResponse.CommentDetail> {
        val comment = commentService.createComment(request)
        return ResponseEntity.ok(comment)
    }

    @PutMapping("/{commentId}")
    fun updateComment(
        @PathVariable commentId: Long,
        @RequestBody request: CommentRequest.CreateOrUpdateComment,
    ): ResponseEntity<CommentResponse.CommentDetail> {
        val updatedComment = commentService.updateComment(commentId, request)
        return ResponseEntity.ok(updatedComment)
    }

    @DeleteMapping("/{commentId}")
    fun deleteComment(
        @PathVariable commentId: Long,
    ): ResponseEntity<Void> {
        commentService.deleteComment(commentId)
        return ResponseEntity.noContent().build()
    }

    @ExceptionHandler
    fun handleException(e: Exception): ResponseEntity<String> {
        val status =
            when (e) {
                is LikeAlreadyExistsException -> 409
                is CommentNotFoundException, is UserNotFoundException, is PostNotFoundException -> 404
                else -> 500
            }
        return ResponseEntity.status(status).build()
    }
}