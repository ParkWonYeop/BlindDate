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
@Entity(name = "user_profile_image")
class UserProfileImageEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    val id: Long? = null,

    @ManyToOne(cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "user_id", nullable = false)
    var user: UserEntity,

    @Column(name = "image_url", nullable = false)
    var imageUrl: String,

    @Column(name = "is_main", nullable = false)
    var isMain: Boolean = false
) : BaseEntity() {
    constructor(
        user: UserEntity,
        imageUrl: String
    ) : this(
        id = null,
        user = user,
        imageUrl = imageUrl
    )
}
