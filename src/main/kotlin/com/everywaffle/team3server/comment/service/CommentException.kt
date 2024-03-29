package com.everywaffle.team3server.comment.service

sealed class CommentException(message: String) : RuntimeException(message)

class CommentNotFoundException(commentId: Long) : CommentException("Comment not found with id: $commentId")
class CommentNeverLikedException(commentId: Long) : CommentException("Comment never liked: $commentId")
class LikeAlreadyExistsException(commentId: Long) : CommentException("Like for post $commentId already exists.")