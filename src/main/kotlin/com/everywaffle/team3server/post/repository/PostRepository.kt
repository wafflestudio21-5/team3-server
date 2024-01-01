package com.everywaffle.team3server.post.repository

import com.everywaffle.team3server.post.model.PostEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<PostEntity, Long>