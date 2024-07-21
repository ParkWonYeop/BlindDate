package org.dblab.blinddate.common.entity.entities

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import org.dblab.blinddate.common.entity.BaseEntity
import org.hibernate.annotations.SQLRestriction

@SQLRestriction("deleted_at IS NULL")
@Entity(name = "permit_profile")
class PermitProfileEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permit_id")
    val id: Long? = null,

    @ManyToOne(cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "profile_id", nullable = false)
    var profile: UserProfileEntity
) : BaseEntity() {
    constructor(
        profile: UserProfileEntity
    ) : this(
        id = null,
        profile = profile
    )
}
