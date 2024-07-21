package org.dblab.blinddate.common.errorHandle.constant

import org.springframework.http.HttpStatus

enum class CommunalResponse constructor(var httpStatus: HttpStatus, var message: String) {
    // AuthResponse
    ALREADY_SIGNUP_PHONENUMBER(HttpStatus.BAD_REQUEST, "이미 가입한 전화번호입니다."),
    USER_NOT_FOUND(HttpStatus.UNAUTHORIZED, "유저를 찾을 수 없습니다."),
    CODE_NOT_FOUND(HttpStatus.BAD_REQUEST, "코드를 찾을 수 없습니다."),
    CODE_NOT_CORRECT(HttpStatus.BAD_REQUEST, "코드가 일치하지 않습니다."),

    // UserResponse
    USER_NOT_ACTIVE(HttpStatus.BAD_REQUEST, "인증되지 않은 유저입니다."),
    USER_PROFILE_NOT_FOUND(HttpStatus.BAD_REQUEST, "유저 프로필을 찾을 수 없습니다."),
    USER_PROFILE_IS_NOT_OPEN(HttpStatus.BAD_REQUEST, "프로필이 비공개 상태입니다."),

    //AdminResponse
    PERMIT_REQUEST_NOT_FOUND(HttpStatus.BAD_REQUEST, "프로필 허용 요청을 찾을 수 없습니다.")
    ;
}
