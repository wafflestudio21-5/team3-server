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
}