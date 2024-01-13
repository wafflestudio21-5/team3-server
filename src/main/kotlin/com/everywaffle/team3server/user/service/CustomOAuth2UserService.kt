package com.everywaffle.team3server.user.service

import com.everywaffle.team3server.user.repository.UserRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service

@Service
class CustomOAuth2UserService(
    private val userRepository: UserRepository,
    private val userSignUpService: UserSignUpService,
    private val userSignInService: UserSignInService,
) : DefaultOAuth2UserService() {
    private val logger: Logger = LoggerFactory.getLogger(CustomOAuth2UserService::class.java)
    override fun loadUser(userRequest: OAuth2UserRequest): OAuth2User {
        // OAuth2UserRequest를 통해 카카오에서 사용자 정보를 로드
        val oAuth2User = super.loadUser(userRequest)
        logger.info("{}", oAuth2User.attributes)

        // 카카오에서 제공하는 사용자 정보를 추출
        val attributes = oAuth2User.attributes
        val kakaoId = ("" + attributes["id"]) as? String ?: ""
        if (kakaoId.isNotEmpty()) {
            val userName = "kakao-$kakaoId"
            val existingUser = userRepository.findByUserName(userName)
            if (existingUser == null) {
                // 사용자가 존재하지 않으면 회원가입
                userSignUpService.signUp(userName = userName, password = "0000", email = "")
                val newUser = userRepository.findByUserName(userName)
                if (newUser != null) {
                    userSignInService.localSignIn(newUser.userName, newUser.password)
                } else {
                    throw UserNotFoundException()
                }
            } else {
                // 이미 존재하는 사용자라면 필요한 경우 로그인 처리
                userSignInService.localSignIn(existingUser.userName, existingUser.password)
            }
        }
        return oAuth2User
    }
}