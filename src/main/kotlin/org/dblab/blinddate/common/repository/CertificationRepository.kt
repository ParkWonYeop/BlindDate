package org.dblab.blinddate.common.repository

import org.dblab.blinddate.common.entity.entities.CertificationEntity
import org.dblab.blinddate.common.entity.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CertificationRepository : JpaRepository<CertificationEntity, Long> {
    fun findByUser(user: UserEntity): CertificationEntity?
}
