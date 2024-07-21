package org.dblab.blinddate.admin.dto

import com.fasterxml.jackson.annotation.JsonProperty
import org.dblab.blinddate.common.enum.BodyTypeEnum
import org.dblab.blinddate.common.enum.EducationEnum
import org.dblab.blinddate.common.enum.GenderEnum
import java.time.LocalDateTime

data class PermitResponseDto(
    val name: String,
    val email: String,
    val gender: GenderEnum,
    val age: Int,
    val height: Int,
    val weight: Int,
    val education: EducationEnum,
    val bodyType: BodyTypeEnum,
    val createdAt: LocalDateTime
)
