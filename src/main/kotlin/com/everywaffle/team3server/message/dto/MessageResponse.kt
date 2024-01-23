package com.everywaffle.team3server.message.dto

import java.util.Date

class MessageResponse {
    data class MessageDetail(
        val messageId: Long,
        val sessionId: Long,
        val senderId: Long,
        val content: String,
        val createdAt: Date,
    )

    data class SessionDetail(
        val sessionId: Long,
        val user1Id: Long,
        val user2Id: Long,
        val lastMessage: MessageDetail?,
    )
}