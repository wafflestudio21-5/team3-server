package com.everywaffle.team3server.post.repository

import org.springframework.data.jpa.repository.JpaRepository

interface PostLikeRepository : JpaRepository<PostLikeEntity, Long>