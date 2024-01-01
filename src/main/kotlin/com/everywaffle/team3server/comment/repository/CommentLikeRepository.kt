package com.everywaffle.team3server.comment.repository

import com.everywaffle.team3server.comment.model.CommentLikeEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CommentLikeRepository : JpaRepository<CommentLikeEntity, Long>