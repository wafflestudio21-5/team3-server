import com.everywaffle.team3server.user.controller.UserDetailController
import com.everywaffle.team3server.user.service.UserDetailService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@ExtendWith(MockitoExtension::class)
class UserDetailControllerTest {

    private lateinit var mockMvc: MockMvc

    @Mock
    private lateinit var userDetailService: UserDetailService

    @InjectMocks
    private lateinit var userDetailController: UserDetailController

    @BeforeEach
    fun setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userDetailController).build()
    }

    @Test
    fun `change password should return OK`() {
        val userId = 1L
        val newPassword = "newPassword123"

        mockMvc.perform(
            post("/api/details/$userId/change-password")
                .param("newPassword", newPassword)
        )
            .andExpect(status().isOk)
    }

    @Test
    fun `change email should return OK`() {
        val userId = 1L
        val newEmail = "newemail@example.com"

        mockMvc.perform(
            post(
                "/api/details/$userId/change-email"
            )
                .param("newEmail", newEmail)
        )
            .andExpect(status().isOk)
    }
}