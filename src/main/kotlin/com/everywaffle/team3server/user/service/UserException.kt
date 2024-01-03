package com.everywaffle.team3server.user.service

sealed class UserException : RuntimeException()

class SignUpUsernameConflictException : UserException()

class SignUpEmailConflictException : UserException()
class SignInUserNameNotFoundException : UserException()

class SignInInvalidPasswordException : UserException()