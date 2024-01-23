package com.everywaffle.team3server.message.model

import com.everywaffle.team3server.user.model.UserEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.util.Date

@Entity
@Table(name = "messages")
class MessageEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @ManyToOne
    @JoinColumn(name = "session_id")
    val session: MessageSessionEntity,
    @Column(nullable = false)
    val content: String,
    @ManyToOne
    @JoinColumn(name = "sender_id")
    val sender: UserEntity,
    @Column(nullable = false)
    val createdAt: Date = Date(),
)