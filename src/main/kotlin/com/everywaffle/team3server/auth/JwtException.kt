package com.everywaffle.team3server.auth

// JWT 생성 중 발생하는 예외
class JwtCreationException(message: String) : RuntimeException(message)

// JWT 검증 중 발생하는 예외
class JwtValidationException(message: String) : RuntimeException(message)

// 기타 JWT 관련 예외
class JwtAuthenticationException(message: String) : RuntimeException(message)