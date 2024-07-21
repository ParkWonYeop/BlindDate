package org.dblab.blinddate.user

import org.dblab.blinddate.common.entity.entities.PermitProfileEntity
import org.dblab.blinddate.common.entity.entities.UserProfileEntity
import org.dblab.blinddate.common.errorHandle.CustomException
import org.dblab.blinddate.common.errorHandle.constant.CommunalResponse
import org.dblab.blinddate.common.repository.PermitProfileRepository
import org.dblab.blinddate.common.repository.ProfileRepository
import org.dblab.blinddate.common.repository.UserRepository
import org.dblab.blinddate.common.utils.SecurityUtil.getCurrentMemberId
import org.dblab.blinddate.user.dto.ProfileDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository,
    private val userProfileRepository: ProfileRepository,
    private val permitProfileRepository: PermitProfileRepository
) {
    @Transactional
    fun registerProfile(profileDto: ProfileDto) {
        val userEntity = userRepository.findByEmail(getCurrentMemberId())
            ?: throw CustomException(CommunalResponse.USER_NOT_FOUND)

        if (!userEntity.isActive) throw CustomException(CommunalResponse.USER_NOT_ACTIVE)

        userProfileRepository.findByUser(userEntity)?.let { userProfile ->
            userProfile.apply {
                height = profileDto.height
                weight = profileDto.weight
                bodyType = profileDto.bodyType
                education = profileDto.education
                isActive = false
            }
            userProfileRepository.save(userProfile)

            permitProfileRepository.findByProfile(userProfile)?.let { permitProfileEntity ->
                permitProfileRepository.delete(permitProfileEntity)
            }
            permitProfileRepository.save(
                PermitProfileEntity(
                    profile = userProfile
                )
            )
        } ?: {
            val userProfile = UserProfileEntity(
                userEntity,
                profileDto.gender,
                profileDto.age,
                profileDto.height,
                profileDto.weight,
                profileDto.bodyType,
                profileDto.education
            )

            userProfileRepository.save(userProfile)

            permitProfileRepository.findByProfile(userProfile)?.let { permitProfileEntity ->
                permitProfileRepository.delete(permitProfileEntity)
            }
            permitProfileRepository.save(
                PermitProfileEntity(
                    profile = userProfile
                )
            )
        }
    }

    @Transactional(readOnly = true)
    fun inquiryProfile(email: String): ProfileDto {
        val userEntity = userRepository.findByEmail(email)
            ?: throw CustomException(CommunalResponse.USER_NOT_FOUND)

        if (!userEntity.isActive) throw CustomException(CommunalResponse.USER_NOT_ACTIVE)

        val userProfileEntity = userProfileRepository.findByUser(userEntity)
            ?: throw CustomException(CommunalResponse.USER_PROFILE_NOT_FOUND)

        if (!userProfileEntity.isOpen) throw CustomException(CommunalResponse.USER_PROFILE_IS_NOT_OPEN)

        return ProfileDto(
            userProfileEntity.gender,
            userProfileEntity.age,
            userProfileEntity.height,
            userProfileEntity.weight,
            userProfileEntity.education,
            userProfileEntity.bodyType
        )
    }

    @Transactional
    fun changeOpenProfile(): UserProfileEntity {
        val userEntity = userRepository.findByEmail(getCurrentMemberId())
            ?: throw CustomException(CommunalResponse.USER_NOT_FOUND)

        if (!userEntity.isActive) throw CustomException(CommunalResponse.USER_NOT_ACTIVE)

        return userProfileRepository.findByUser(userEntity)?.apply {
            isOpen = !isOpen
        } ?: throw CustomException(CommunalResponse.USER_PROFILE_NOT_FOUND)
    }
}
