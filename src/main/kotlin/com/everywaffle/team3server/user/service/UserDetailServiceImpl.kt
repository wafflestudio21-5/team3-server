package com.everywaffle.team3server.user.service

import com.everywaffle.team3server.user.dto.UserDetailRequest
import com.everywaffle.team3server.user.dto.UserDetailResponse
import com.everywaffle.team3server.user.model.UserDetailEntity
import com.everywaffle.team3server.user.repository.UserDetailRepository
import com.everywaffle.team3server.user.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class UserDetailServiceImpl(
    private val userDetailRepository: UserDetailRepository,
    private val userRepository: UserRepository,
) : UserDetailService {
    @Transactional
    override fun createOrUpdateUserDetail(
        userId: Long,
        userDetail: UserDetailRequest.UserDetail,
    ): UserDetailResponse.UserDetail {
        val user =
            userRepository.findById(userId).orElseThrow {
                RuntimeException("User not found with id: $userId")
            }

        val userDetailSaveEntity =
            UserDetailEntity(
                realName = userDetail.realName,
                nickname = userDetail.nickname,
                department = userDetail.department,
                studentId = userDetail.studentId,
                user = user,
            )

        val savedUserDetail = userDetailRepository.save(userDetailSaveEntity)

        return UserDetailResponse.UserDetail(
            userId = savedUserDetail.user.userId,
            realName = savedUserDetail.realName,
            nickname = savedUserDetail.nickname,
            department = savedUserDetail.department,
            studentId = savedUserDetail.studentId,
        )
    }

    override fun getUserDetail(userId: Long): UserDetailResponse.UserDetail {
        val user = userRepository.findById(userId).orElseThrow {
            RuntimeException("User not found with id: $userId")
        }
        val userDetail = userDetailRepository.findByUser(user)
            ?: throw RuntimeException("User Detail not found with id: $userId")

        return UserDetailResponse.UserDetail(
            userId = user.userId,
            realName = userDetail.realName,
            nickname = userDetail.nickname,
            department = userDetail.department,
            studentId = userDetail.studentId,
        )
    }
    @Transactional
    override fun changePassword(userId: Long, newPassword: String): UserDetailResponse.UserDetail {
        val user = userRepository.findById(userId).orElseThrow {
            RuntimeException("User not found with id: $userId")
        }

        // UserEntity 객체 업데이트
        user.password = newPassword
        val updatedUser = userRepository.save(user)

        // UserDetailEntity 찾기 및 업데이트
        val userDetail = userDetailRepository.findByUser(updatedUser)
            ?: throw RuntimeException("User Detail not found with id: $userId")

        val savedUserDetail = userDetailRepository.save(userDetail)

        return UserDetailResponse.UserDetail(
            userId = savedUserDetail.user.userId,
            realName = savedUserDetail.realName,
            nickname = savedUserDetail.nickname,
            department = savedUserDetail.department,
            studentId = savedUserDetail.studentId,
        )
    }

    override fun changeEmail(userId: Long, newEmail: String): UserDetailResponse.UserDetail {
        val user = userRepository.findById(userId).orElseThrow {
            RuntimeException("User not found with id: $userId")
        }

        // UserEntity 객체의 이메일 필드를 직접 업데이트
        user.email = newEmail
        userRepository.save(user)

        return getUserDetail(userId)
    }
}