package com.everywaffle.team3server.comment.controller

import com.everywaffle.team3server.comment.dto.CommentResponse
import com.everywaffle.team3server.comment.service.CommentService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/post")
class PostCommentController(private val commentService: CommentService) {
    @GetMapping("/{postId}/comments")
    fun getCommentsByPostId(
        @PathVariable postId: Long,
    ): ResponseEntity<List<CommentResponse.CommentDetailList>> {
        val comments = commentService.getCommentsByPostId(postId)
        return ResponseEntity.ok(comments)
    }
}