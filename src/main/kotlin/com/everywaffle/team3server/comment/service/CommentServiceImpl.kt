package com.everywaffle.team3server.comment.service

import com.everywaffle.team3server.comment.dto.CommentRequest
import com.everywaffle.team3server.comment.dto.CommentResponse
import com.everywaffle.team3server.comment.model.CommentEntity
import com.everywaffle.team3server.comment.repository.CommentRepository
import com.everywaffle.team3server.post.repository.PostRepository
import com.everywaffle.team3server.post.service.PostNotFoundException
import com.everywaffle.team3server.user.repository.UserRepository
import com.everywaffle.team3server.user.service.UserNotFoundException
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class CommentServiceImpl(
    private val commentRepository: CommentRepository,
    private val userRepository: UserRepository,
    private val postRepository: PostRepository,
) : CommentService {
    @Transactional
    override fun createComment(request: CommentRequest.CreateOrUpdateComment): CommentResponse.CommentDetail {
        val user =
            userRepository.findById(request.userId).orElseThrow {
                UserNotFoundException()
            }
        val post =
            postRepository.findById(request.postId).orElseThrow {
                PostNotFoundException(request.postId)
            }
        val parentComment =
            request.parentCommentId?.let {
                commentRepository.findById(it).orElse(null)
            }

        val newComment =
            CommentEntity(
                user = user,
                post = post,
                content = request.content,
                parentComment = parentComment,
            )

        val savedComment = commentRepository.save(newComment)

        return CommentResponse.CommentDetail(
            id = savedComment.commentId,
            userId = savedComment.user.userId,
            postId = savedComment.post.postId,
            content = savedComment.content,
            parentCommentId = savedComment.parentComment?.commentId,
            createdAt = savedComment.createdAt,
        )
    }

    @Transactional
    override fun updateComment(
        commentId: Long,
        request: CommentRequest.CreateOrUpdateComment,
    ): CommentResponse.CommentDetail {
        val comment =
            commentRepository.findById(
                commentId,
            ).orElseThrow {
                CommentNotFoundException(commentId)
            }

        comment.apply {
            content = request.content
        }

        val updatedComment = commentRepository.save(comment)
        return CommentResponse.CommentDetail(
            id = updatedComment.commentId,
            userId = updatedComment.user.userId,
            postId = updatedComment.post.postId,
            content = updatedComment.content,
            parentCommentId = updatedComment.parentComment?.commentId,
            createdAt = updatedComment.createdAt,
        )
    }

    @Transactional
    override fun deleteComment(commentId: Long) {
        val comment =
            commentRepository.findById(commentId).orElseThrow {
                CommentNotFoundException(commentId)
            }
        commentRepository.delete(comment)
    }
}