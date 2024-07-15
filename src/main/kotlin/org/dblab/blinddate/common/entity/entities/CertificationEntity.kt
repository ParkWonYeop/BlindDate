package org.dblab.blinddate.common.entity.entities

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.MapsId
import jakarta.persistence.OneToOne
import org.dblab.blinddate.common.entity.BaseEntity
import org.hibernate.annotations.SQLRestriction

@SQLRestriction("deleted_at IS NULL")
@Entity(name = "user_certification")
class CertificationEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    val id: Long? = null,

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id", nullable = false)
    var user: UserEntity,

    @Column(name = "code", nullable = false)
    var code: String
) : BaseEntity() {
    constructor(
        user: UserEntity,
        code: String
    ) : this(
        id = null,
        user = user,
        code = code
    )
}
