package org.dblab.blinddate.auth.dto

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import org.dblab.blinddate.common.validation.ValidationGroups.NotBlankGroup
import org.dblab.blinddate.common.validation.ValidationGroups.PatternGroup

data class LoginDto @JsonCreator constructor(
    @field:NotBlank(message = "빈 문자열 입니다.", groups = [NotBlankGroup::class])
    @JsonProperty("email")
    val email: String,

    @field:Pattern(regexp = "[a-zA-Z1-9]{6,12}", message = "비밀번호는 영어와 숫자로 포함해서 6~12자리 이내로 입력해주세요.", groups = [PatternGroup::class])
    @field:NotBlank(message = "빈 문자열 입니다.", groups = [NotBlankGroup::class])
    @JsonProperty("password")
    val password: String
)
