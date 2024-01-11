package com.everywaffle.team3server.user.dto

class UserDetailRequest {
    data class UserDetail(
        val realName: String,
        val nickname: String,
        val department: String,
        val studentId: Int,
    )
}