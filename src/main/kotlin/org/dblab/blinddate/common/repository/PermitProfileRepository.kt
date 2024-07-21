package org.dblab.blinddate.common.repository

import org.dblab.blinddate.common.entity.entities.PermitProfileEntity
import org.dblab.blinddate.common.entity.entities.UserProfileEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PermitProfileRepository : JpaRepository<PermitProfileEntity, Long> {
    fun findByProfile(profile: UserProfileEntity): PermitProfileEntity?
    fun findByPermitId(permitId: Long): PermitProfileEntity?
}
