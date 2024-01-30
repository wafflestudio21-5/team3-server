package com.everywaffle.team3server.post.service

sealed class PostException(message: String) : RuntimeException(message)

class PostNotFoundException(postId: Long) : PostException("Post not found with id: $postId")
class PostNeverLikedException(postId: Long) : PostException("Post never liked: $postId")
class PostNeverScrapedException(postId: Long) : PostException("Post never scraped: $postId")
class LikeAlreadyExistsException(postId: Long) : PostException("Like for post $postId already exists.")
class ScrapAlreadyExistsException(postId: Long) : PostException("Scrap for post $postId already exists.")
class UnauthorizedPostAccessException(userId: Long, postId: Long) : PostException(
    "User with id: $userId is not authorized to access post with id: $postId",
)

class PostVoteAlreadyExistsException(postId: Long) : PostException("Vote for post $postId already exists.")

class PostNeverVotedException(postId: Long) : PostException("Post never voted: $postId")

class PostMakeVoteAlreadyExistsException(postId: Long) : PostException("Make vote for post $postId already exists.")

class AlreadyVotingPostException(postId: Long) : PostException("Post is already being voted: $postId")

class IsNotVotingException(postId: Long) : PostException("Post $postId is not a voting post")