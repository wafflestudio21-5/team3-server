package com.everywaffle.team3server.message.service

import com.everywaffle.team3server.message.dto.MessageRequest
import com.everywaffle.team3server.message.dto.MessageResponse
import com.everywaffle.team3server.message.dto.MessageSessionRequest
import com.everywaffle.team3server.message.model.MessageEntity
import com.everywaffle.team3server.message.model.MessageSessionEntity
import com.everywaffle.team3server.message.repository.MessageRepository
import com.everywaffle.team3server.message.repository.MessageSessionRepostiory
import com.everywaffle.team3server.user.repository.UserRepository
import com.everywaffle.team3server.user.service.UserNotFoundException
import org.springframework.stereotype.Service
import java.util.Date

@Service
class MessageServiceImpl(
    private val messageSessionRepository: MessageSessionRepostiory,
    private val messageRepository: MessageRepository,
    private val userRepository: UserRepository,
) : MessageService {
    override fun createSession(request: MessageSessionRequest.CreateSession): Long {
        val user1 = userRepository.findById(request.user1Id).orElseThrow { UserNotFoundException() }
        val user2 = userRepository.findById(request.user2Id).orElseThrow { UserNotFoundException() }

        val session = MessageSessionEntity(user1 = user1, user2 = user2)
        return messageSessionRepository.save(session).id
    }

    override fun sendMessage(request: MessageRequest.SendMessage): MessageResponse.MessageDetail {
        val session = messageSessionRepository.findById(request.sessionId).orElseThrow { SessionNotFoundException(request.sessionId) }
        val sender = userRepository.findById(request.senderId).orElseThrow { UserNotFoundException() }

        val message = MessageEntity(session = session, sender = sender, content = request.content, createdAt = Date())
        val savedMessage = messageRepository.save(message)

        return MessageResponse.MessageDetail(
            messageId = savedMessage.id,
            sessionId = savedMessage.session.id,
            senderId = savedMessage.sender.userId,
            content = savedMessage.content,
            createdAt = savedMessage.createdAt,
        )
    }

    override fun getSessionList(userId: Long): List<MessageResponse.SessionDetail> {
        val sessions = messageSessionRepository.findByUser1UserIdOrUser2UserId(userId, userId)
        return sessions.map { session ->
            val lastMessage = messageRepository.findTopBySessionIdOrderByCreatedAtDesc(session.id)
            MessageResponse.SessionDetail(
                sessionId = session.id,
                user1Id = session.user1.userId,
                user2Id = session.user2.userId,
                lastMessage =
                lastMessage?.let {
                    MessageResponse.MessageDetail(
                        messageId = it.id,
                        sessionId = it.session.id,
                        senderId = it.sender.userId,
                        content = it.content,
                        createdAt = it.createdAt,
                    )
                },
            )
        }
    }

    override fun getMessageList(sessionId: Long): List<MessageResponse.MessageDetail> {
        val messages = messageRepository.findBySessionIdOrderByCreatedAtDesc(sessionId)
        return messages.map { message ->
            MessageResponse.MessageDetail(
                messageId = message.id,
                sessionId = message.session.id,
                senderId = message.sender.userId,
                content = message.content,
                createdAt = message.createdAt,
            )
        }
    }
}