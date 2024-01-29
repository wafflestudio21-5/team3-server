package com.everywaffle.team3server.post.controller

import com.everywaffle.team3server.post.dto.PostRequest
import com.everywaffle.team3server.post.dto.PostResponse
import com.everywaffle.team3server.post.model.Category
import com.everywaffle.team3server.post.service.PostException
import com.everywaffle.team3server.post.service.PostNotFoundException
import com.everywaffle.team3server.post.service.PostService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/post")
class PostController(private val postService: PostService) {
    @PostMapping
    fun createPost(
        @RequestBody request: PostRequest.CreateOrUpdatePost,
    ): ResponseEntity<PostResponse.PostDetail> {
        val post = postService.createPost(request)
        return ResponseEntity.ok(post)
    }

    @PutMapping("/{postId}")
    fun updatePost(
        @PathVariable postId: Long,
        @RequestBody request: PostRequest.CreateOrUpdatePost,
    ): ResponseEntity<PostResponse.PostDetail> {
        val updatedPost = postService.updatePost(postId, request)
        return ResponseEntity.ok(updatedPost)
    }

    @DeleteMapping("/{postId}")
    fun deletePost(
        @PathVariable postId: Long,
    ): ResponseEntity<Void> {
        postService.deletePost(postId)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/{postId}")
    fun getPost(
        @PathVariable postId: Long,
    ): ResponseEntity<PostResponse.PostDetail> {
        val post = postService.getPostById(postId) ?: throw PostNotFoundException(postId)
        return ResponseEntity.ok(post)
    }

    @GetMapping("/category/{category}")
    fun getCategoryPost(
        @PathVariable category: Category,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int,
    ): ResponseEntity<List<PostResponse.PostDetail>> {
        val postList = postService.getCategoryPost(category, page, size)
        return ResponseEntity.ok(postList.content)
    }

    @GetMapping("/trending")
    fun getTrendingPost(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int,
    ): ResponseEntity<List<PostResponse.PostDetail>> {
        val postList = postService.getTrendingPost(page, size)
        return ResponseEntity.ok(postList.content)
    }

    @GetMapping("/search/{category}")
    fun searchCategoryPost(
        @PathVariable category: Category,
        @RequestParam(required = true) keyword: String,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int,
    ): ResponseEntity<List<PostResponse.PostDetail>> {
        val postList = postService.searchPost(keyword, category, page, size)
        return ResponseEntity.ok(postList.content)
    }

    @GetMapping("/search")
    fun searchPost(
        @RequestParam(required = true) keyword: String,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int,
    ): ResponseEntity<List<PostResponse.PostDetail>> {
        val postList = postService.searchPost(keyword, null, page, size)
        return ResponseEntity.ok(postList.content)
    }

    @ExceptionHandler
    fun handlePostException(e: PostException): ResponseEntity<Unit> {
        val status =
            when (e) {
                is PostNotFoundException -> 404
                // 다른 PostException 서브클래스에 대한 처리를 여기에 추가할 수 있습니다.
                else -> 404 // 기본적으로 처리되지 않은 예외에 대한 상태 코드
            }
        return ResponseEntity.status(status).build()
    }
}