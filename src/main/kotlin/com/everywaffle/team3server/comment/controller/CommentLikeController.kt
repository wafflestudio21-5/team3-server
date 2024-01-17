package com.everywaffle.team3server.comment.controller;

import com.everywaffle.team3server.comment.dto.CommentRequest
import com.everywaffle.team3server.comment.service.CommentLikeService;
import com.everywaffle.team3server.user.detail.CustomUserDetails
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

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
}
