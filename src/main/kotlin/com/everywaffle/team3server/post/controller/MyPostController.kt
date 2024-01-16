package com.everywaffle.team3server.post.controller

import com.everywaffle.team3server.post.dto.PostResponse
import com.everywaffle.team3server.post.service.MyPostService
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/post")
class MyPostController(
    private val myPostService: MyPostService
) {

    @GetMapping("/myposts")
    fun myPosts(
        @RequestParam user: Long,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int
    ): ResponseEntity<Page<PostResponse.PostDetail>> {
        val posts = myPostService.getMyPosts(user, page, size)
        return ResponseEntity.ok(posts)
    }

    @GetMapping("/myscrapped")
    fun myScrapped(
        @RequestParam user: Long,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int
    ): ResponseEntity<Page<PostResponse.PostDetail>> {
        val posts = myPostService.getMyScrappedPosts(user, page, size)
        return ResponseEntity.ok(posts)
    }

    @GetMapping("/mycommented")
    fun myCommented(
        @RequestParam user: Long,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int
    ): ResponseEntity<Page<PostResponse.PostDetail>> {
        val posts = myPostService.getMyCommentedPosts(user, page, size)
        return ResponseEntity.ok(posts)
    }
}