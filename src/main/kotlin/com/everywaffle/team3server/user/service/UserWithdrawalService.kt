package com.everywaffle.team3server.user.service

interface UserWithdrawalService {
    fun withdrawal(userName: String, password: String)
}