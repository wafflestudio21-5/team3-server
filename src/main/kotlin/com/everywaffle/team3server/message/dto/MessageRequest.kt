package com.everywaffle.team3server.message.dto

class MessageRequest {
    data class SendMessage(
        val sessionId: Long,
        val senderId: Long,
        val content: String,
    )
}