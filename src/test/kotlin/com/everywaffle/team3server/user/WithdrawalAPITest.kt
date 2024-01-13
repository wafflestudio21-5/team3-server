package com.everywaffle.team3server.user

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers


@AutoConfigureMockMvc
@SpringBootTest
class WithdrawalAPITest @Autowired constructor (
    private val mvc: MockMvc,
    private val mapper: ObjectMapper,
) {

    @Test
    fun `회원 탈퇴 성공 시 200 응답을 내려준다`() {
        mvc.perform(
            MockMvcRequestBuilders.post("/api/signup")
                .content(
                    mapper.writeValueAsString(
                        mapOf(
                            "userName" to "every",
                            "password" to "waffle",
                            "email" to "hello@everywaffle.com"
                        )
                    )
                )
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().`is`(200))

        val signInResponse = mvc.perform(
            MockMvcRequestBuilders.post("/api/signin")
                .content(
                    mapper.writeValueAsString(
                        mapOf(
                            "userName" to "every",
                            "password" to "waffle"
                        )
                    )
                )
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().`is`(200))
            .andReturn()

        val signInResponseBody = signInResponse.response.contentAsString
        val token = mapper.readValue<Map<String, Any>>(signInResponseBody).get("token") as String

        mvc.perform(
            MockMvcRequestBuilders.get("/api/withdrawal")
                .header(HttpHeaders.AUTHORIZATION, "Bearer $token")
                .content(
                    mapper.writeValueAsString(
                        mapOf(
                            "password" to "waffle"
                        )
                    )
                )
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().`is`(200))
    }

    @Test
    fun `비밀번호가 틀렸을 경우 404 응답을 내려준다`() {
        mvc.perform(
            MockMvcRequestBuilders.post("/api/signup")
                .content(
                    mapper.writeValueAsString(
                        mapOf(
                            "userName" to "every",
                            "password" to "waffle",
                            "email" to "hello@everywaffle.com"
                        )
                    )
                )
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().`is`(200))

        val signInResponse = mvc.perform(
            MockMvcRequestBuilders.post("/api/signin")
                .content(
                    mapper.writeValueAsString(
                        mapOf(
                            "userName" to "every",
                            "password" to "waffle"
                        )
                    )
                )
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().`is`(200))
            .andReturn()

        val signInResponseBody = signInResponse.response.contentAsString
        val token = mapper.readValue<Map<String, Any>>(signInResponseBody).get("token") as String

        mvc.perform(
            MockMvcRequestBuilders.get("/api/withdrawal")
                .header(HttpHeaders.AUTHORIZATION, "Bearer $token")
                .content(
                    mapper.writeValueAsString(
                        mapOf(
                            "password" to "waffleee"
                        )
                    )
                )
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().`is`(404))
    }

}