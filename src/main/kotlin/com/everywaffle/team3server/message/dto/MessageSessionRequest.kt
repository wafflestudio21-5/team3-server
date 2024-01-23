package com.everywaffle.team3server.message.dto

class MessageSessionRequest {
    data class CreateSession(
        val user1Id: Long,
        val user2Id: Long,
    )
}