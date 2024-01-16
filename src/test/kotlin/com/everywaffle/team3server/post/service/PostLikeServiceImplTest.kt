package com.everywaffle.team3server.post.service


import com.everywaffle.team3server.post.model.Category
import com.everywaffle.team3server.post.model.PostEntity
import com.everywaffle.team3server.post.model.PostLikeEntity
import com.everywaffle.team3server.post.repository.PostLikeRepository
import com.everywaffle.team3server.post.repository.PostRepository
import com.everywaffle.team3server.user.model.UserEntity
import com.everywaffle.team3server.user.repository.UserRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.*


@SpringBootTest
class PostLikeServiceImplTest @Autowired constructor(
    private val postLikeService: PostLikeService,
    private val postLikeRepository: PostLikeRepository,
    private val userRepository: UserRepository,
    private val postRepository: PostRepository,
)
{

    @BeforeEach
    fun beforeEach() {
        val userEntity = UserEntity(userId = 1L, userName = "Test", password = "Test", email = "Test@Test.com")
        val postEntity = PostEntity(
            postId = 1L,
            user = userEntity,
            title = "Test",
            content = "Test",
            category = Category.FREE_BOARD,
            createdAt = Date(),
            likes = 0,
        )
        userRepository.save(userEntity)
        postRepository.save(postEntity)
    }

    @Test
    fun `좋아요 성공 및 데이터 저장 확인`() {
        val userId = 1L
        val postId = 1L

        postLikeService.create(postId, userId)

        val postLike = postLikeRepository.findByPostPostIdAndUserUserId(postId,userId)
        Assertions.assertNotNull(postLike, "PostLikeEntity should not be null")

        Assertions.assertEquals(postLike?.post?.postId, 1L)
        Assertions.assertEquals(postLike?.user?.userId, 1L)
        Assertions.assertEquals(postLike?.post?.likes, 1)
    }
}
