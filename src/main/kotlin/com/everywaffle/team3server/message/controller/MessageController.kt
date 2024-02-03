package com.everywaffle.team3server.message.controller

import com.everywaffle.team3server.message.dto.MessageRequest
import com.everywaffle.team3server.message.dto.MessageResponse
import com.everywaffle.team3server.message.dto.MessageSessionRequest
import com.everywaffle.team3server.message.service.MessageService
import com.everywaffle.team3server.message.service.SessionNotFoundException
import com.everywaffle.team3server.user.service.UserNotFoundException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/messages")
class MessageController(private val messageService: MessageService) {
    @PostMapping("/session")
    fun createSession(
        @RequestBody request: MessageSessionRequest.CreateSession,
    ): ResponseEntity<Long> {
        val sessionId = messageService.createSession(request)
        return ResponseEntity.ok(sessionId)
    }

    @PostMapping("/send")
    fun sendMessage(
        @RequestBody request: MessageRequest.SendMessage,
    ): ResponseEntity<MessageResponse.MessageDetail> {
        val messageDetail = messageService.sendMessage(request)
        return ResponseEntity.ok(messageDetail)
    }

    // 랜덤 세션이 아닌 것들을 반환하는 API
    @GetMapping("/sessions/{userId}")
    fun getSessionList(
        @PathVariable userId: Long,
    ): ResponseEntity<List<MessageResponse.SessionDetail>> {
        val sessions = messageService.getSessionList(userId, isRandom = false)
        return ResponseEntity.ok(sessions)
    }

    // 랜덤 세션만 반환하는 API
    @GetMapping("/sessions-random/{userId}")
    fun getRandomSessionList(
        @PathVariable userId: Long,
    ): ResponseEntity<List<MessageResponse.SessionDetail>> {
        val sessions = messageService.getSessionList(userId, isRandom = true)
        return ResponseEntity.ok(sessions)
    }

    @GetMapping("/session/{sessionId}")
    fun getMessageList(
        @PathVariable sessionId: Long,
    ): ResponseEntity<List<MessageResponse.MessageDetail>> {
        val messages = messageService.getMessageList(sessionId)
        return ResponseEntity.ok(messages)
    }

    @PostMapping("/random")
    fun sendRandomMessage(
        @RequestBody request: MessageRequest.SendMessage,
    ): ResponseEntity<MessageResponse.MessageDetail> {
        val messageDetail = messageService.sendRandomMessage(request.senderId, request.content)
        return ResponseEntity.ok(messageDetail)
    }

    @ExceptionHandler
    fun handleMessageException(e: RuntimeException): ResponseEntity<String> {
        val status =
            when (e) {
                is UserNotFoundException, is SessionNotFoundException -> 404
                else -> 500 // 기본적으로 처리되지 않은 예외에 대한 상태 코드
            }
        return ResponseEntity.status(status).body(e.message)
    }
}