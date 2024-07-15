package org.dblab.blinddate.common.config

import org.dblab.blinddate.common.properties.MailProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl
import java.util.*

@Configuration
class MailConfig(private val mailProperties: MailProperty) {
    @Bean
    protected fun javaMailSender(): JavaMailSender = JavaMailSenderImpl().also {
        it.host = mailProperties.host
        it.username = mailProperties.username
        it.password = mailProperties.password
        it.port = mailProperties.port

        val properties = Properties()

        properties["mail.smtp.socketFactory.port"] = mailProperties.port
        properties["mail.smtp.auth"] = mailProperties.auth
        properties["mail.smtp.starttls.enable"] = mailProperties.tls
        properties["mail.smtp.starttls.required"] = mailProperties.tls
        properties["mail.smtp.socketFactory.fallback"] = false
        properties["mail.smtp.socketFactory.class"] = true

        it.javaMailProperties = properties
        it.defaultEncoding = "UTF-8"
    }
}
