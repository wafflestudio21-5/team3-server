package com.everywaffle.team3server.message.repository

import com.everywaffle.team3server.message.model.MessageSessionEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface MessageSessionRepository : JpaRepository<MessageSessionEntity, Long> {
    @Query(
        "SELECT ms FROM MessageSessionEntity ms WHERE (ms.user1.userId = :userId OR ms.user2.userId = :userId) AND ms.isRandom = :isRandom",
    )
    fun findByUserAndIsRandom(
        @Param("userId") userId: Long,
        @Param("isRandom") isRandom: Boolean,
    ): List<MessageSessionEntity>
}