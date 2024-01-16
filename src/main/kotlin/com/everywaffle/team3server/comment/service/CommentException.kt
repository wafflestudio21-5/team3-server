package com.everywaffle.team3server.comment.service

import com.everywaffle.team3server.post.service.PostException

sealed class CommentException(message: String) : RuntimeException(message)

class CommentNotFoundException(commentId: Long) : CommentException("Comment not found with id: $commentId")
class CommentNeverLikedException(commentId: Long) : CommentException("Comment never liked: $commentId")
