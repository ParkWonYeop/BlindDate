package org.dblab.blinddate.common.utils

import org.springframework.security.core.context.SecurityContextHolder

class SecurityUtil {
    fun getCurrentMemberId(): String {
        val authentication = SecurityContextHolder.getContext().authentication
            ?: throw RuntimeException("No authentication information.")
        return authentication.name
            ?:throw RuntimeException("No authentication information.")
    }
}