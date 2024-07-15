package org.dblab.blinddate.common.entity.entities

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToOne
import org.dblab.blinddate.common.entity.BaseEntity
import org.dblab.blinddate.common.enum.GenderEnum
import org.dblab.blinddate.common.enum.PermissionEnum
import org.hibernate.annotations.SQLRestriction

@SQLRestriction("deleted_at IS NULL")
@Entity(name = "users")
class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "email", nullable = false)
    var email: String,

    @Column(name = "password", nullable = false)
    var password: String,

    @Column(name = "name", nullable = false)
    var name: String,

    @Column(name = "nick_name", nullable = false)
    var nickName: String,

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    var gender: GenderEnum,

    @Column(name = "is_active", nullable = false)
    var isActive: Boolean = false,

    @Column(name = "permission", nullable = false)
    @Enumerated(EnumType.STRING)
    var permission: PermissionEnum = PermissionEnum.USER,
) : BaseEntity() {
    constructor(
        email: String,
        password: String,
        name: String,
        nickName: String,
        gender: GenderEnum
    ) : this(
        id = null,
        email = email,
        password = password,
        name = name,
        nickName = nickName,
        gender = gender
    )
}
