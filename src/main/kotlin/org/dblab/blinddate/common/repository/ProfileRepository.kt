package org.dblab.blinddate.common.repository

import org.dblab.blinddate.common.entity.entities.UserEntity
import org.dblab.blinddate.common.entity.entities.UserProfileEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ProfileRepository : JpaRepository<UserProfileEntity, Long> {
    fun findByUser(user: UserEntity): UserProfileEntity?
}
