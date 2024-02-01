package com.everywaffle.team3server.comment.service

import com.everywaffle.team3server.comment.model.CommentLikeEntity
import com.everywaffle.team3server.comment.repository.CommentLikeRepository
import com.everywaffle.team3server.comment.repository.CommentRepository
import com.everywaffle.team3server.post.service.LikeAlreadyExistsException
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
        if (exists(commentId, userId)) {
            throw LikeAlreadyExistsException(commentId)
        }
        val user = userRepository.findById(userId).orElseThrow { UserNotFoundException() }
        val comment = commentRepository.findById(commentId).orElseThrow { CommentNotFoundException(commentId) }

        val commentLike = CommentLikeEntity(comment = comment, user = user)
        commentLikeRepository.save(commentLike)

        commentRepository.incrementLikes(commentId)
    }

    @Synchronized
    override fun createSynchronized(commentId: Long, userId: Long) {
        create(commentId, userId)
    }

    override fun delete(commentId: Long, userId: Long) {
        val like = commentLikeRepository.findByCommentCommentIdAndUserUserId(commentId, userId)
            ?: throw CommentNeverLikedException(commentId)

        commentRepository.decrementLikes(commentId)

        commentLikeRepository.delete(like)
    }
}