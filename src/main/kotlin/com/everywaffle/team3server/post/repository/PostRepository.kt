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
    fun findAllByCategory(
        category: Category,
        pageable: PageRequest,
    ): Page<PostEntity>

    @Query(
        """
        SELECT p FROM posts p WHERE p.category = :category
        AND (LOWER(p.title) LIKE LOWER(CONCAT('%', :titleKeyword, '%'))
        OR LOWER(p.content) LIKE LOWER(CONCAT('%', :contentKeyword, '%')))
    """
    )
    fun findAllByCategoryAndTitleContainingOrContentContaining(
        category: Category,
        titleKeyword: String,
        contentKeyword: String,
        pageable: PageRequest,
    ): Page<PostEntity>

    fun findAllByTitleContainingOrContentContaining(
        titleKeyword: String,
        contentKeyword: String,
        pageable: PageRequest,
    ): Page<PostEntity>

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

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(
        """
        UPDATE posts p SET p.agree = p.agree + 1 WHERE p.postId = :postId
    """
    )
    fun incrementAgree(postId: Long)

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(
        """
        UPDATE posts p SET p.disagree = p.disagree + 1 WHERE p.postId = :postId
    """
    )
    fun incrementDisagree(postId: Long)

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(
        """
        UPDATE posts p SET p.agree = p.agree - 1 WHERE p.postId = :postId
    """
    )
    fun decrementAgree(postId: Long)

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(
        """
        UPDATE posts p SET p.disagree = p.disagree - 1 WHERE p.postId = :postId
    """
    )
    fun decrementDisagree(postId: Long)

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(
        """
        UPDATE posts p SET p.makeVoteCnt = p.makeVoteCnt + 1 WHERE p.postId = :postId
    """
    )
    fun incrementMakeVoteCnt(postId: Long)

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(
        """
        UPDATE posts p SET p.makeVoteCnt = p.makeVoteCnt - 1 WHERE p.postId = :postId
    """
    )
    fun decrementMakeVoteCnt(postId: Long)
}