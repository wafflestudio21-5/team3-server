import com.everywaffle.team3server.auth.JwtTokenProvider
import com.everywaffle.team3server.user.dto.OAuth2AuthenticationResponse
import com.everywaffle.team3server.user.repository.UserRepository
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
import java.io.IOException

class OAuth2AuthenticationSuccessHandler(
    private val tokenProvider: JwtTokenProvider,
    private val objectMapper: ObjectMapper,
    private val userRepository: UserRepository,
) : SimpleUrlAuthenticationSuccessHandler() {

    @Throws(IOException::class)
    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        val oAuth2User = authentication.principal as OAuth2User
        val username = oAuth2User.name // OAuth2 인증으로부터 사용자 식별 정보 추출
        val userEntity = userRepository.findByUserName("kakao-$username") ?: throw RuntimeException("User not found")
        val authResponse = OAuth2AuthenticationResponse(
            userName = userEntity?.userName ?: "",
            passWord = userEntity?.password ?: ""
        )
        response.addHeader("Authorization", "Bearer " + tokenProvider.createToken(authResponse.userName))
        response.contentType = "application/json;charset=UTF-8"
        response.writer.write(objectMapper.writeValueAsString(authResponse))
    }
}