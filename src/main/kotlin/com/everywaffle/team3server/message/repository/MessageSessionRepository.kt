package com.everywaffle.team3server.message.repository

import com.everywaffle.team3server.message.model.MessageSessionEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MessageSessionRepository : JpaRepository<MessageSessionEntity, Long> {
    fun findByUser1UserIdOrUser2UserId(
        user1Id: Long,
        user2Id: Long,
    ): List<MessageSessionEntity>
}