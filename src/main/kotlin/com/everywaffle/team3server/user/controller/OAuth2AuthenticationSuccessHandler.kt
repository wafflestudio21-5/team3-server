import com.everywaffle.team3server.auth.JwtTokenProvider
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
import java.io.IOException

class OAuth2AuthenticationSuccessHandler(private val tokenProvider: JwtTokenProvider) :
    SimpleUrlAuthenticationSuccessHandler() {
    @Throws(IOException::class)
    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        val oAuth2User = authentication?.principal as OAuth2User
        val username = oAuth2User.name // OAuth2 인증으로부터 사용자 식별 정보 추출
        val token = tokenProvider.createToken(username) // JWT 토큰 생성

        response?.addHeader("Authorization", "Bearer $token")
    }
}