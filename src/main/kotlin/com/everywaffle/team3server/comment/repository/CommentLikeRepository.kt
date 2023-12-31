package com.everywaffle.team3server.comment.repository

import org.springframework.data.jpa.repository.JpaRepository

interface CommentLikeRepository : JpaRepository<CommentLikeEntity, Long>