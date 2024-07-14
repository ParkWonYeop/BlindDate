package org.dblab.blinddate.common.errorHandle

import org.dblab.blinddate.common.errorHandle.constant.CommunalResponse
import org.springframework.http.HttpStatus

class CustomException(communalResponse: CommunalResponse) : RuntimeException() {
    override var message: String = communalResponse.message
    var httpStatus: HttpStatus = communalResponse.httpStatus
}