package com.everywaffle.team3server.message.model

import com.everywaffle.team3server.user.model.UserEntity
import jakarta.persistence.*

@Entity
@Table(name = "message_sessions")
class MessageSessionEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "session_id")
    val id: Long = 0,
    @ManyToOne
    @JoinColumn(name = "user1_id")
    val user1: UserEntity,
    @ManyToOne
    @JoinColumn(name = "user2_id")
    val user2: UserEntity,
    @OneToMany(mappedBy = "session")
    val messages: List<MessageEntity> = mutableListOf(),
)