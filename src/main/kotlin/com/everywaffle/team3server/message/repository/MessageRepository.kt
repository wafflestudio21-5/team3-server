package com.everywaffle.team3server.message.repository

import com.everywaffle.team3server.message.model.MessageEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MessageRepository : JpaRepository<MessageEntity, Long> {
    fun findBySessionId(sessionId: Long): List<MessageEntity>

    fun findTopBySessionIdOrderByCreatedAtDesc(sessionId: Long): MessageEntity?
}