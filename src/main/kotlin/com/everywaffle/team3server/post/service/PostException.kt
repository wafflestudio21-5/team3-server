package com.everywaffle.team3server.post.service

sealed class PostException(message: String) : RuntimeException(message)

class PostNotFoundException(postId: Long) : PostException("Post not found with id: $postId")

class PostNeverLikedException(postId: Long) : PostException("Post never liked: $postId")
class PostNeverScrapedException(postId: Long) : PostException("Post never scraped: $postId")
class UnauthorizedPostAccessException(userId: Long, postId: Long) : PostException(
    "User with id: $userId is not authorized to access post with id: $postId",
)