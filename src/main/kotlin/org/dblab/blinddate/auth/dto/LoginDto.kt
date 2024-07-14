package org.dblab.blinddate.auth.dto

import org.dblab.blinddate.common.validation.ValidationGroups.NotBlankGroup
import org.dblab.blinddate.common.validation.ValidationGroups.PatternGroup
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

data class LoginDto @JsonCreator constructor(
    @field:Pattern(regexp = "[0-9]{8,12}", message = "전화번호 형식을 맞춰주세요.", groups = [PatternGroup::class])
    @field:NotBlank(message = "빈 문자열 입니다.", groups = [NotBlankGroup::class])
    @JsonProperty("phoneNumber")
    val email: String,

    @field:Pattern(regexp = "[a-zA-Z1-9]{6,12}", message = "비밀번호는 영어와 숫자로 포함해서 6~12자리 이내로 입력해주세요.", groups = [PatternGroup::class])
    @field:NotBlank(message = "빈 문자열 입니다.", groups = [NotBlankGroup::class])
    @JsonProperty("password")
    val password: String,
)
