package com.everywaffle.team3server.user.service

sealed class UserException : RuntimeException()

class SignInUserNameNotFoundException : UserException()

class SignInInvalidPasswordException : UserException()
