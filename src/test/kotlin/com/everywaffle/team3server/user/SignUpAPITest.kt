package com.everywaffle.team3server.user

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.transaction.annotation.Transactional

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
class SignUpAPITest @Autowired constructor (
    private val mvc: MockMvc,
    private val mapper: ObjectMapper,
) {
    @Test
    fun `회원가입시에 유저 이름 이메일이 충돌할 경우 409 응답을 내려준다`() {
        mvc.perform(
            post("/api/signup")
                .content(
                    mapper.writeValueAsString(
                        mapOf(
                            "userName" to "bad",
                            "password" to "correct",
                            "email" to "waffle@everywaffle.com"
                        )
                    )
                )
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().`is`(200))

        mvc.perform(
            post("/api/signup")
                .content(
                    mapper.writeValueAsString(
                        mapOf(
                            "userName" to "bad",
                            "password" to "correct",
                            "email" to "waffle1@everywaffle.com"
                        )
                    )
                )
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().`is`(409))

        mvc.perform(
            post("/api/signup")
                .content(
                    mapper.writeValueAsString(
                        mapOf(
                            "userName" to "bad1",
                            "password" to "correct",
                            "email" to "waffle@everywaffle.com"
                        )
                    )
                )
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().`is`(409))
    }
}