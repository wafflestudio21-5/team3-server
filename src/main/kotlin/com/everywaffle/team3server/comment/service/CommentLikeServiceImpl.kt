package com.everywaffle.team3server.comment.service

import com.everywaffle.team3server.comment.model.CommentLikeEntity
import com.everywaffle.team3server.comment.repository.CommentLikeRepository
import com.everywaffle.team3server.comment.repository.CommentRepository
import com.everywaffle.team3server.user.repository.UserRepository
import com.everywaffle.team3server.user.service.UserNotFoundException
import org.springframework.stereotype.Service

@Service
class CommentLikeServiceImpl(
    private val commentLikeRepository: CommentLikeRepository,
    private val commentRepository: CommentRepository,
    private val userRepository: UserRepository,
) : CommentLikeService {
    override fun exists(commentId: Long, userId: Long): Boolean {
        return commentLikeRepository.findByCommentCommentIdAndUserUserId(commentId, userId) != null
    }

    override fun create(commentId: Long, userId: Long) {
        val user = userRepository.findById(userId).orElseThrow { UserNotFoundException() }
        val comment = commentRepository.findById(commentId).orElseThrow { CommentNotFoundException(commentId) }

        val commentLike = CommentLikeEntity(comment = comment, user = user)
        commentLikeRepository.save(commentLike)

        commentRepository.incrementLikes(commentId)
    }

    @Synchronized
    override fun createSynchronized(postId: Long, userId: Long) {
        create(postId, userId)
    }

    override fun delete(commentId: Long, userId: Long) {
        val like = commentLikeRepository.findByCommentCommentIdAndUserUserId(commentId, userId)
            ?: throw CommentNeverLikedException(commentId)
        val comment = commentRepository.findById(commentId).orElseThrow { CommentNotFoundException(commentId) }

        commentRepository.decrementLikes(commentId)

        commentLikeRepository.delete(like)
    }
}