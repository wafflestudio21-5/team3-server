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
            likes = savedComment.likes,
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
            likes = updatedComment.likes,
        )
    }

    @Transactional
    override fun deleteComment(commentId: Long) {
        val comment =
            commentRepository.findById(commentId).orElseThrow {
                CommentNotFoundException(commentId)
            }

        if (commentRepository.hasChildren(comment.commentId)) {
            comment.content = "삭제된 댓글입니다."
            commentRepository.save(comment)
        } else {
            commentRepository.delete(comment)
        }
    }

    @Transactional
    override fun getCommentsByPostId(postId: Long): List<CommentResponse.CommentDetailList> {
        val comments = commentRepository.findByPostPostIdOrderByCreatedAt(postId)
        val commentMap = comments.associateBy { it.commentId }
        val childCommentsMap = mutableMapOf<Long, MutableList<CommentResponse.CommentDetail>>()

        for (comment in comments) {
            if (comment.parentComment
                != null
            ) {
                val parentCommentId = comment.parentComment.commentId
                val childCommentDetail =
                    CommentResponse.CommentDetail(
                        id = comment.commentId,
                        userId = comment.user.userId,
                        postId = comment.post.postId,
                        content = comment.content,
                        createdAt = comment.createdAt,
                        parentCommentId = parentCommentId,
                        likes = comment.likes,
                    )
                childCommentsMap.computeIfAbsent(parentCommentId) { mutableListOf() }.add(childCommentDetail)
            }
        }

        val result = mutableListOf<CommentResponse.CommentDetailList>()
        for (comment in comments) {
            if (comment.parentComment == null) {
                val commentDetailInstance =
                    CommentResponse.CommentDetailList(
                        id = comment.commentId,
                        userId = comment.user.userId,
                        postId = comment.post.postId,
                        content = comment.content,
                        createdAt = comment.createdAt,
                        childComments = childCommentsMap[comment.commentId] ?: listOf(),
                        likes = comment.likes,
                    )
                result.add(commentDetailInstance)
            }
        }
        return result
    }
}