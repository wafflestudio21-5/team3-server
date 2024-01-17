package com.everywaffle.team3server.post.controller

import com.everywaffle.team3server.post.dto.PostRequest
import com.everywaffle.team3server.post.service.PostLikeService
import com.everywaffle.team3server.user.detail.CustomUserDetails
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/post")
class PostLikeController(private val postLikeService: PostLikeService) {
    @PostMapping("/{postId}/likes")
    fun likePost(@PathVariable postId: Long,
                 @RequestBody request: PostRequest.CreateOrUpdatePostLike): ResponseEntity<Void> {
        postLikeService.create(postId, request.userId)
        return ResponseEntity.ok().build()
    }
}