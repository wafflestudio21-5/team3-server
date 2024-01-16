package com.everywaffle.team3server.post.repository

import com.everywaffle.team3server.post.model.Category
import com.everywaffle.team3server.post.model.PostEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<PostEntity, Long> {
    fun findByCategory(category: Category): List<PostEntity>
}