package com.everywaffle.team3server.message.service

import com.everywaffle.team3server.message.dto.MessageRequest
import com.everywaffle.team3server.message.dto.MessageResponse
import com.everywaffle.team3server.message.dto.MessageSessionRequest

interface MessageService {
    fun createSession(request: MessageSessionRequest.CreateSession): Long

    fun sendMessage(request: MessageRequest.SendMessage): MessageResponse.MessageDetail

    fun getSessionList(userId: Long): List<MessageResponse.SessionDetail>

    fun getMessageList(sessionId: Long): List<MessageResponse.MessageDetail>
}