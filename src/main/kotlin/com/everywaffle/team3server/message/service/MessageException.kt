package com.everywaffle.team3server.message.service

sealed class MessageException(message: String) : RuntimeException(message)

class SessionNotFoundException(sessionId: Long) : MessageException("Message session not found with id: $sessionId")