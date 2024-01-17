package com.everywaffle.team3server.post.controller

import com.everywaffle.team3server.post.dto.ScrapRequest
import com.everywaffle.team3server.post.dto.ScrapResponse
import com.everywaffle.team3server.post.service.ScrapServiceImpl
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/post")
class PostScrapController(private val scrapService: ScrapServiceImpl) {
    @PostMapping("/scrap")
    fun scrapPost(@RequestBody scrapRequest: ScrapRequest.ScrapPost): ResponseEntity<ScrapResponse.ScrapDetail> {
        val scrapDetail = scrapService.create(scrapRequest.postId, scrapRequest.userId)
        return ResponseEntity.ok(scrapDetail)
    }
}