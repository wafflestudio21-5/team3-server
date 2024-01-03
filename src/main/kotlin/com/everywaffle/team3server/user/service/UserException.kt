package com.everywaffle.team3server.user.service

sealed class UserException : RuntimeException()

class SignUpUsernameConflictException : UserException()

class SignUpEmailConflictException : UserException()