package org.dblab.blinddate.common.repository

import org.dblab.blinddate.common.entity.entities.TokenEntity
import org.dblab.blinddate.common.entity.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface TokenRepository : JpaRepository<TokenEntity, Long> {
    fun findByUser(user: UserEntity): TokenEntity?
}