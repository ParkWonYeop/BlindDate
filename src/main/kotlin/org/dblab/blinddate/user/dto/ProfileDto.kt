package org.dblab.blinddate.user.dto

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import org.dblab.blinddate.common.enum.BodyTypeEnum
import org.dblab.blinddate.common.enum.EducationEnum
import org.dblab.blinddate.common.enum.GenderEnum

data class ProfileDto @JsonCreator constructor(
    @JsonProperty("gender")
    val gender: GenderEnum,

    @JsonProperty("age")
    val age: Int,

    @JsonProperty("height")
    val height: Int,

    @JsonProperty("weight")
    val weight: Int,

    @JsonProperty("gender")
    val education: EducationEnum,

    @JsonProperty("bodyType")
    val bodyType: BodyTypeEnum
)
