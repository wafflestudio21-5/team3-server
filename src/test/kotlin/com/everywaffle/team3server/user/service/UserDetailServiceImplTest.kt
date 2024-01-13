import com.everywaffle.team3server.user.model.UserDetailEntity
import com.everywaffle.team3server.user.model.UserEntity
import com.everywaffle.team3server.user.repository.UserDetailRepository
import com.everywaffle.team3server.user.repository.UserRepository
import com.everywaffle.team3server.user.service.UserDetailServiceImpl
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import java.util.Optional

class UserDetailServiceImplTest {
    private lateinit var userRepository: UserRepository
    private lateinit var userDetailRepository: UserDetailRepository
    private lateinit var userDetailService: UserDetailServiceImpl


    @BeforeEach
    fun setUp() {
        userRepository = Mockito.mock(UserRepository::class.java)
        userDetailRepository = Mockito.mock(UserDetailRepository::class.java)
        userDetailService = UserDetailServiceImpl(userDetailRepository, userRepository)

        val testUser = UserEntity(userId = 1L, userName = "testUser", password = "password123", email = "test@example.com")
        val testUserDetail = UserDetailEntity(id = 1L, realName = "Test Name", nickname = "Test Nickname", department = "Test Department", studentId = 123456, user = testUser)

        Mockito.`when`(userRepository.findById(1L)).thenReturn(Optional.of(testUser))
        Mockito.`when`(userRepository.findByUserId(1L)).thenReturn(testUser) // 수정된 라인
        Mockito.`when`(userDetailRepository.findByUser(testUser)).thenReturn(testUserDetail)

        Mockito.`when`(userRepository.save(ArgumentMatchers.any(UserEntity::class.java)))
            .thenAnswer { invocation -> invocation.getArgument(0) }
        Mockito.`when`(userDetailRepository.save(ArgumentMatchers.any(UserDetailEntity::class.java)))
            .thenAnswer { invocation -> invocation.getArgument(0) }
    }




    @Test
    fun testChangePassword() {
        val newPassword = "newPassword"
        val updatedUserDetail = userDetailService.changePassword(1L, newPassword)

        // userRepository.findByUserId를 호출하고 null 체크
        val updatedUser = userRepository.findByUserId(updatedUserDetail.userId) ?: throw AssertionError("User not found")

        // 검증
        Assertions.assertEquals(newPassword, updatedUser.password)
        Assertions.assertEquals(1L, updatedUser.userId)
    }
    @Test
    fun testChangeEmail() {
        val newEmail = "newemail@example.com"
        val updatedUserDetail = userDetailService.changeEmail(1L, newEmail)

        // userRepository.findByUserId를 호출하고 null 체크
        val updatedUser = userRepository.findByUserId(updatedUserDetail.userId) ?: throw AssertionError("User not found")

        // 검증
        Assertions.assertEquals(newEmail, updatedUser.email)
        Assertions.assertEquals(1L, updatedUser.userId)
    }
}
