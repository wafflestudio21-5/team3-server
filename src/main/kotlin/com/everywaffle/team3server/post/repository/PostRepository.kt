package com.everywaffle.team3server.post.repository

import com.everywaffle.team3server.post.model.Category
import com.everywaffle.team3server.post.model.PostEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.transaction.annotation.Transactional

interface PostRepository : JpaRepository<PostEntity, Long> {
    fun findTopByCategoryOrderByCreatedAtDesc(category: Category): PostEntity?

    fun findTop2ByLikesGreaterThanOrderByCreatedAtDesc(likesThreshold: Int): List<PostEntity>
    @Modifying
    @Transactional
    @Query(
        """
        UPDATE posts p SET p.likes = p.likes + 1 WHERE p.postId = :postId
    """
    )
    fun incrementLikes(postId: Long)

    @Modifying
    @Transactional
    @Query(
        """
        UPDATE posts p SET p.likes = p.likes - 1 WHERE p.postId = :postId
    """
    )
    fun decrementLikes(postId: Long)

    @Query("select p from posts p where p.user.userId = :userId")
    fun findByUserId(userId: Long, pageable: PageRequest): Page<PostEntity>
}