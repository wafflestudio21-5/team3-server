package com.everywaffle.team3server.post.service

import com.everywaffle.team3server.post.model.Category
import com.everywaffle.team3server.post.model.PostEntity
import com.everywaffle.team3server.post.model.ScrapEntity
import com.everywaffle.team3server.post.repository.ScrapRepository
import com.everywaffle.team3server.user.model.UserEntity
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.boot.test.context.SpringBootTest
import java.util.Date

@ExtendWith(MockitoExtension::class)
@SpringBootTest
class ScrapServiceImplTest {

    @Mock
    private lateinit var scrapRepository: ScrapRepository

    @Mock
    private lateinit var scrapService: ScrapServiceImpl

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
        val scrap = ScrapEntity(post = postEntity, user = userEntity, scrapId = 1L)
        `when`(scrapRepository.findByPostPostIdAndUserUserId(1L, 1L)).thenReturn(scrap)
    }

    @Test
    fun `게시글 스크랩 성공 확인`() {
        val userId = 1L
        val postId = 1L

        scrapService.create(postId, userId)
        val scrapEntity = scrapRepository.findByPostPostIdAndUserUserId(postId, userId)
        assertNotNull(scrapEntity, "ScrapEntity should not be null")

        assertEquals(scrapEntity?.post?.postId, 1L)
        assertEquals(scrapEntity?.user?.userId, 1L)
    }
}