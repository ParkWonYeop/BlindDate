package org.dblab.blinddate.auth.dto

data class LoginResponseDto(
    var refreshToken: String,
    var accessToken: String,
)
