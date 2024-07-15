package org.dblab.blinddate.auth.dto

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import org.dblab.blinddate.common.validation.ValidationGroups.NotBlankGroup

data class CertificationDto @JsonCreator constructor(
    @field:NotBlank(message = "빈 문자열 입니다.", groups = [NotBlankGroup::class])
    @JsonProperty("email")
    val email: String,

    @field:NotBlank(message = "빈 문자열 입니다.", groups = [NotBlankGroup::class])
    @JsonProperty("code")
    val code: String
)
