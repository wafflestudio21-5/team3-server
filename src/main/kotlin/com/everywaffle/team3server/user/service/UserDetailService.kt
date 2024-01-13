package com.everywaffle.team3server.user.service

import com.everywaffle.team3server.user.dto.UserDetailRequest
import com.everywaffle.team3server.user.dto.UserDetailResponse

interface UserDetailService {
    fun createOrUpdateUserDetail(
        userId: Long,
        userDetail: UserDetailRequest.UserDetail,
    ): UserDetailResponse.UserDetail

    fun getUserDetail(
        userId: Long
    ): UserDetailResponse.UserDetail
    fun changePassword(
        userId: Long,
        newPassword: String
    ): UserDetailResponse.UserDetail
    fun changeEmail(
        userId: Long,
        newEmail: String
    ): UserDetailResponse.UserDetail
}