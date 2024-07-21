package org.dblab.blinddate.common.entity.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.MapsId
import jakarta.persistence.OneToOne
import org.dblab.blinddate.common.entity.BaseEntity
import org.dblab.blinddate.common.enum.BodyTypeEnum
import org.dblab.blinddate.common.enum.EducationEnum
import org.dblab.blinddate.common.enum.GenderEnum
import org.hibernate.annotations.SQLRestriction

@SQLRestriction("deleted_at IS NULL")
@Entity(name = "user_profile")
class UserProfileEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    val id: Long? = null,

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id", nullable = false)
    var user: UserEntity,

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    var gender: GenderEnum,

    @Column(name = "age", nullable = false)
    var age: Int,

    @Column(name = "height", nullable = false)
    var height: Int,

    @Column(name = "weight", nullable = false)
    var weight: Int,

    @Column(name = "body_type", nullable = false)
    @Enumerated(EnumType.STRING)
    var bodyType: BodyTypeEnum,

    @Column(name = "education", nullable = false)
    @Enumerated(EnumType.STRING)
    var education: EducationEnum,

    @Column(name = "is_open", nullable = false)
    var isOpen: Boolean = false,

    @Column(name = "is_active", nullable = false)
    var isActive: Boolean = false
) : BaseEntity() {
    constructor(
        user: UserEntity,
        gender: GenderEnum,
        age: Int,
        height: Int,
        weight: Int,
        bodyType: BodyTypeEnum,
        education: EducationEnum
    ) : this(
        id = null,
        gender = gender,
        age = age,
        user = user,
        height = height,
        weight = weight,
        bodyType = bodyType,
        education = education
    )
}
