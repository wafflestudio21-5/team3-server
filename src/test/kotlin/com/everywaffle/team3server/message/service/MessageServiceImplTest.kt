package com.everywaffle.team3server.message.service

import com.everywaffle.team3server.message.repository.MessageRepository
import com.everywaffle.team3server.message.repository.MessageSessionRepository
import com.everywaffle.team3server.user.model.UserEntity
import com.everywaffle.team3server.user.repository.UserRepository
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
class MessageServiceImplTest
@Autowired
constructor(
    private val userRepository: UserRepository,
    private val messageRepository: MessageRepository,
    private val messageSessionRepository: MessageSessionRepository,
) {
    private lateinit var user1: UserEntity
    private lateinit var user2: UserEntity

    @BeforeEach
    fun setUp() {
        user1 = UserEntity(1L, "Tester1", "1", "Tester1")
        user2 = UserEntity(2L, "Tester2", "2", "Tester2")

        userRepository.save(user1)
        userRepository.save(user2)
    }
//    @Test
//    fun testSendRandomMessage() {
//        val messageService = MessageServiceImpl(messageSessionRepository, messageRepository, userRepository)
//
//        // user1이 랜덤 쪽지 보내기 테스트
//        val content = "Hello, Random User!"
//        val result = messageService.sendRandomMessage(user1.userId, content)
//
//        // 결과 검증
//        assertNotNull(result)
//        assertEquals(content, result.content)
//
//        // 메시지와 세션을 데이터베이스에서 검증
//        val session = messageSessionRepository.findById(result.sessionId).orElse(null)
//        assertNotNull(session)
//        val messages = messageRepository.findBySessionIdOrderByCreatedAtDesc(session.id)
//        assertTrue(messages.any { it.content == content && it.sender.userId == user1.userId })
//    }
}