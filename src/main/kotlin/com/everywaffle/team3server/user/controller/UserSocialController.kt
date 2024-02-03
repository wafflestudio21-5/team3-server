package com.everywaffle.team3server.user.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class UserSocialController {
    @GetMapping("/api/signup/kakao")
    fun kakaoLogin(): String {
        return "redirect:/oauth2/authorization/kakao"
    }
}