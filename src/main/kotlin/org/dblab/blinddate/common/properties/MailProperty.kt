package org.dblab.blinddate.common.properties

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class MailProperty(
    @Value("\${spring.mail.host}")
    val host: String,
    @Value("\${spring.mail.port}")
    val port: Int,
    @Value("\${spring.mail.username}")
    var username: String,
    @Value("\${spring.mail.password}")
    val password: String,
    @Value("\${spring.mail.properties.mail.smtp.auth}")
    val auth: Boolean,
    @Value("\${spring.mail.properties.mail.smtp.starttls.enable}")
    val tls: Boolean
)
