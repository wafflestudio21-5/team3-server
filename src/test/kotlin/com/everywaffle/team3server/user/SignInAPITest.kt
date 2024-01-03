package com.everywaffle.team3server.user

import com.everywaffle.team3server.user.model.UserEntity
import com.everywaffle.team3server.user.repository.UserSignInRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@AutoConfigureMockMvc
@SpringBootTest
class SignInAPITest @Autowired constructor(
    private val mvc: MockMvc,
    private val mapper: ObjectMapper,
){
    @MockBean
    private lateinit var userSignInRepository: UserSignInRepository

    @BeforeEach
    fun setup() {
        val user = UserEntity(
            userName = "test",
            password = "password",
            email = "test@email.com"
        )
        whenever(userSignInRepository.findByUserName("test")).thenReturn(user)
    }
    @Test
    fun `로그인 시 유효하지 않은 경우 404 응답을 내려준다`(){
        mvc.perform(
            post("/api/signin")
                .content(
                    mapper.writeValueAsString(
                        mapOf(
                            "userName" to "test",
                            "password" to "password"
                        )
                    )
                )
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().`is`(200))
        mvc.perform(
            post("/api/signin")
                .content(
                    mapper.writeValueAsString(
                        mapOf(
                            "userName" to "bad",
                            "password" to "password"
                        )
                    )
                )
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().`is`(404))
        mvc.perform(
            post("/api/signin")
                .content(
                    mapper.writeValueAsString(
                        mapOf(
                            "userName" to "test",
                            "password" to "bad"
                        )
                    )
                )
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().`is`(404))
    }



}