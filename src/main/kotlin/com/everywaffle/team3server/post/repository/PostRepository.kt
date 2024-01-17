package com.everywaffle.team3server.post.repository

import com.everywaffle.team3server.post.model.Category
import com.everywaffle.team3server.post.model.PostEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface PostRepository : JpaRepository<PostEntity, Long> {
    fun findAllByCategory(
        category: Category,
        pageable: PageRequest,
    ): Page<PostEntity>

    fun findAllByCategoryAndTitleContainingOrContentContaining(
        category: Category,
        titleKeyword: String,
        contentKeyword: String,
        pageable: PageRequest,
    ): Page<PostEntity>

    fun findAllByTitleContainingOrContentContaining(
        titleKeyword: String,
        contentKeyword: String,
        pageable: PageRequest,
    ): Page<PostEntity>

    fun findTopByCategoryOrderByCreatedAtDesc(category: Category): PostEntity?

    fun findTop2ByLikesGreaterThanOrderByCreatedAtDesc(likesThreshold: Int): List<PostEntity>

    @Query("select p from posts p where p.user.userId = :userId")
    fun findByUserId(userId: Long, pageable: PageRequest): Page<PostEntity>
}