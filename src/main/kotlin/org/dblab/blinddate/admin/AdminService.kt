package org.dblab.blinddate.admin

import org.dblab.blinddate.admin.dto.PermitResponseDto
import org.dblab.blinddate.common.errorHandle.CustomException
import org.dblab.blinddate.common.errorHandle.constant.CommunalResponse
import org.dblab.blinddate.common.repository.PermitProfileRepository
import org.dblab.blinddate.common.repository.ProfileRepository
import org.dblab.blinddate.common.repository.UserRepository
import org.dblab.blinddate.common.utils.SecurityUtil.getCurrentMemberId
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminService(
    private val permitProfileRepository: PermitProfileRepository,
    private val userRepository: UserRepository,
    private val profileRepository: ProfileRepository
) {
    @Transactional(readOnly = true)
    fun getPermitList() = ArrayList<PermitResponseDto>().apply {
        permitProfileRepository.findAll().forEach {
            this.add(
                PermitResponseDto(
                    it.profile.user.name,
                    it.profile.user.email,
                    it.profile.gender,
                    it.profile.age,
                    it.profile.height,
                    it.profile.weight,
                    it.profile.education,
                    it.profile.bodyType,
                    it.createdAt
                )
            )
        }
    }

    @Transactional
    fun permitProfile(email : String) {
        val userEntity = userRepository.findByEmail(getCurrentMemberId())
            ?: throw CustomException(CommunalResponse.USER_NOT_FOUND)

        val profileEntity = profileRepository.findByUser(userEntity)
            ?:throw CustomException(CommunalResponse.USER_PROFILE_NOT_FOUND)

        val permitProfileEntity = permitProfileRepository.findByProfile(profileEntity)
            ?:throw CustomException(CommunalResponse.PERMIT_REQUEST_NOT_FOUND)

        profileEntity.isActive = true
        profileRepository.save(profileEntity)
        permitProfileRepository.delete(permitProfileEntity)
    }
}