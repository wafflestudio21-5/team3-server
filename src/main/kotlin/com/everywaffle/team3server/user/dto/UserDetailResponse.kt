package com.everywaffle.team3server.user.dto

class UserDetailResponse {
    data class UserDetail(
        val userId: Long,
        val realName: String,
        val nickname: String,
        val department: String,
        val studentId: Int,
    )
}