package org.dblab.blinddate.common.errorHandle.constant

import org.springframework.http.HttpStatus

enum class CommunalResponse constructor(var httpStatus: HttpStatus, var message: String) {
    // AuthResponse
    ALREADY_SIGNUP_PHONENUMBER(HttpStatus.BAD_REQUEST, "이미 가입한 전화번호입니다."),
    USER_NOT_FOUND(HttpStatus.UNAUTHORIZED, "유저를 찾을 수 없습니다."),
    CODE_NOT_FOUND(HttpStatus.BAD_REQUEST, "코드를 찾을 수 없습니다."),
    CODE_NOT_CORRECT(HttpStatus.BAD_REQUEST, "코드가 일치하지 않습니다.")
    ;
}
