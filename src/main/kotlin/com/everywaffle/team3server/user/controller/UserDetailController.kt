package com.everywaffle.team3server.user.controller

import com.everywaffle.team3server.user.dto.UserDetailRequest
import com.everywaffle.team3server.user.dto.UserDetailResponse
import com.everywaffle.team3server.user.service.SignInInvalidPasswordException
import com.everywaffle.team3server.user.service.SignInUserNameNotFoundException
import com.everywaffle.team3server.user.service.SignUpEmailConflictException
import com.everywaffle.team3server.user.service.SignUpUsernameConflictException
import com.everywaffle.team3server.user.service.UserDetailService
import com.everywaffle.team3server.user.service.UserException
import com.everywaffle.team3server.user.service.UserNotFoundException
import com.everywaffle.team3server.user.service.WithdrawalInvalidPasswordException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/details")
class UserDetailController(private val userDetailService: UserDetailService) {
    @PostMapping("/{id}")
    fun createUserDetail(
        @PathVariable id: Long,
        @RequestBody userDetailDto: UserDetailRequest.UserDetail,
    ): ResponseEntity<UserDetailResponse.UserDetail> {
        val updatedUserDetail = userDetailService.createOrUpdateUserDetail(id, userDetailDto)
        return ResponseEntity.ok(updatedUserDetail)
    }

    @GetMapping("/{id}")
    fun getUserDetail(
        @PathVariable id: Long,
    ): ResponseEntity<UserDetailResponse.UserDetail> {
        val userDetail = userDetailService.getUserDetail(id)
        return ResponseEntity.ok(userDetail)
    }
    @PostMapping("/{id}/change-password")
    fun changePassword(
        @PathVariable id: Long,
        @RequestParam newPassword: String
    ): ResponseEntity<Unit> {
        userDetailService.changePassword(id, newPassword)
        return ResponseEntity.ok().build()
    }

    @PostMapping("/{id}/change-email")
    fun changeEmail(
        @PathVariable id: Long,
        @RequestParam newEmail: String
    ): ResponseEntity<Unit> {
        userDetailService.changeEmail(id, newEmail)
        return ResponseEntity.ok().build()
    }
    @ExceptionHandler
    fun handleException(e: UserException): ResponseEntity<Unit> {
        val status =
            when (e) {
                is SignInUserNameNotFoundException, is SignInInvalidPasswordException, is UserNotFoundException, is WithdrawalInvalidPasswordException -> 404
                is SignUpUsernameConflictException, is SignUpEmailConflictException -> 409
            }
        return ResponseEntity.status(status).build()
    }
}