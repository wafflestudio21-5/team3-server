package com.everywaffle.team3server.post.repository

import com.everywaffle.team3server.post.model.ScrapEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ScrapRepository : JpaRepository<ScrapEntity, Long> {
    @Query("select s from scraps s where s.user.userId = :userId")
    fun findByUserId(userId: Long, pageable: PageRequest): Page<ScrapEntity>
}