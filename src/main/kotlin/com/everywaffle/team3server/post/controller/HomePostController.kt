package com.everywaffle.team3server.post.controller

import com.everywaffle.team3server.post.dto.PostResponse
import com.everywaffle.team3server.post.model.Category
import com.everywaffle.team3server.post.service.HomePostService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/home")
class HomePostController(private val homePostService: HomePostService) {
    @GetMapping("/post/{category}")
    fun getLatestPostByCategory(
        @PathVariable category: Category,
    ): ResponseEntity<PostResponse.PostDetail> {
        val post = homePostService.getLatestPostByCategory(category)
        return if (post != null) ResponseEntity.ok(post) else ResponseEntity.notFound().build()
    }

    @GetMapping("/post-trending")
    fun getTrendingPosts(): ResponseEntity<List<PostResponse.PostDetail>> {
        val posts = homePostService.getTrendingPosts()
        return ResponseEntity.ok(posts)
    }
}