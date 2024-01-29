package com.everywaffle.team3server.post.controller

import com.everywaffle.team3server.post.dto.ScrapRequest
import com.everywaffle.team3server.post.dto.ScrapResponse
import com.everywaffle.team3server.post.service.ScrapAlreadyExistsException
import com.everywaffle.team3server.post.service.ScrapServiceImpl
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/post")
class PostScrapController(private val scrapService: ScrapServiceImpl) {
    @PostMapping("/{postId}/scrap")
    fun scrapPost(
        @PathVariable postId: Long,
        @RequestBody scrapRequest: ScrapRequest.ScrapPost,
    ): ResponseEntity<ScrapResponse.ScrapDetail> {
        val scrapDetail = scrapService.create(postId, scrapRequest.userId)
        return ResponseEntity.ok(scrapDetail)
    }
    @ExceptionHandler
    fun handleScrapAlreadyExistsException(e: ScrapAlreadyExistsException): ResponseEntity<Unit> {
        val status =
            when (e) {
                is ScrapAlreadyExistsException -> 409
                else -> 404
            }
        return ResponseEntity.status(status).build()
    }
}