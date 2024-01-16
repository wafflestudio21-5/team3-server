package com.everywaffle.team3server.post.repository

import com.everywaffle.team3server.post.model.Category
import com.everywaffle.team3server.post.model.PostEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<PostEntity, Long> {
    fun findAllByCategory(category: Category, pageable: PageRequest): Page<PostEntity>

    fun findAllByCategoryAndTitleContainingOrContentContaining(
        category: Category,
        titleKeyword: String,
        contentKeyword: String,
        pageable: PageRequest
    ): Page<PostEntity>

    fun findAllByTitleContainingOrContentContaining(
        titleKeyword: String,
        contentKeyword: String,
        pageable: PageRequest
    ): Page<PostEntity>
}