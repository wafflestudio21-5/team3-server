package com.everywaffle.team3server.comment.controller;

import com.everywaffle.team3server.comment.service.CommentLikeService;
import com.everywaffle.team3server.user.detail.CustomUserDetails
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comment")
class CommentLikeController(private val commentLikeService: CommentLikeService) {
    @PostMapping("/{commentId}/likes")
    fun likePost(
        @PathVariable commentId: Long,
        @AuthenticationPrincipal userDetails: CustomUserDetails
    ): ResponseEntity<Void> {
        commentLikeService.create(commentId, userDetails.getUser().userId)
        return ResponseEntity.ok().build()
    }
}
