package com.everywaffle.team3server.post.controller

import com.everywaffle.team3server.post.service.PostLikeService
import com.everywaffle.team3server.user.detail.CustomUserDetails
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/post")
class PostLikeController(private val postLikeService: PostLikeService) {
    @PostMapping("/{postId}/likes")
    fun likePost(@PathVariable postId: Long, @AuthenticationPrincipal userDetails: CustomUserDetails): ResponseEntity<Void> {
        postLikeService.create(postId, userDetails.getUser().userId)
        return ResponseEntity.ok().build()
    }
}