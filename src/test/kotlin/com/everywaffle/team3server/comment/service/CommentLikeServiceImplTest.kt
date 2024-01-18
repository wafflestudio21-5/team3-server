package com.everywaffle.team3server.comment.service

import com.everywaffle.team3server.comment.model.CommentEntity
import com.everywaffle.team3server.comment.repository.CommentLikeRepository
import com.everywaffle.team3server.comment.repository.CommentRepository
import com.everywaffle.team3server.post.model.Category
import com.everywaffle.team3server.post.model.PostEntity
import com.everywaffle.team3server.post.repository.PostRepository
import com.everywaffle.team3server.user.model.UserEntity
import com.everywaffle.team3server.user.repository.UserRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.Date

@SpringBootTest
class CommentLikeServiceImplTest @Autowired constructor(
    private val commentLikeService: CommentLikeService,
    private val commentLikeRepository: CommentLikeRepository,
    private val userRepository: UserRepository,
    private val postRepository: PostRepository,
    private val commentRepository: CommentRepository,
) {
    @BeforeEach
    fun beforeEach() {
        val userEntity = UserEntity(userId = 1L, userName = "Test", password = "Test", email = "Test@Test.com")
        val postEntity = PostEntity(
            postId = 1L,
            user = userEntity,
            title = "Test",
            content = "Test",
            category = Category.FREE_BOARD,
            createdAt = Date()
        )
        val commentEntity = CommentEntity(
            commentId = 1L,
            post = postEntity,
            user = userEntity,
            content = "commentTest",
            createdAt = Date(),
        )
        userRepository.save(userEntity)
        postRepository.save(postEntity)
        commentRepository.save(commentEntity)
    }

    @Test
    fun `좋아요 성공 및 데이터 저장 확인`() {
        val userId = 1L
        val commentId = 1L

        commentLikeService.create(commentId, userId)

        val commentLike = commentLikeRepository.findByCommentCommentIdAndUserUserId(commentId, userId)
        Assertions.assertNotNull(commentLike, "CommentLikeEntity should not be null")

        Assertions.assertEquals(commentLike?.comment?.commentId, 1L)
        Assertions.assertEquals(commentLike?.user?.userId, 1L)
        Assertions.assertEquals(commentLike?.comment?.likes, 1)
    }

    @Test
    fun `좋아요 취소 및 데이터 저장 확인`() {
        val userId = 1L
        val commentId = 1L

        commentLikeService.create(commentId, userId)

        val commentLike = commentLikeRepository.findByCommentCommentIdAndUserUserId(commentId, userId)

        Assertions.assertEquals(commentLike?.comment?.likes, 1)

        commentLikeService.delete(commentId, userId)

        val commentLike2 = commentRepository.findById(1L)

        org.assertj.core.api.Assertions.assertThat(commentLike2.get().likes).isEqualTo(0)
    }
}