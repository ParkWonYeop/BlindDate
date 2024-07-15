package org.dblab.blinddate.common.utils

import jakarta.mail.internet.MimeMessage
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Component

@Component
class MailUtil(private val javaMailSender: JavaMailSender) {
    @Value("\${spring.mail.username}")
    private lateinit var writerUrl: String

    fun createMail(email: String, code: String) = javaMailSender.createMimeMessage().also {
        val title = "이메일 인증"

        it.addRecipients(MimeMessage.RecipientType.TO, email)
        it.subject = title
        it.setFrom(writerUrl)

        var msgOfEmail = ""
        msgOfEmail += "<div style='margin:20px;'>"
        msgOfEmail += "<h1> 이메일 인증입니다. </h1>"
        msgOfEmail += "<br>"
        msgOfEmail += "<p>아래 코드를 입력해주세요<p>"
        msgOfEmail += "<br>"
        msgOfEmail += "<p>$code<p>"
        msgOfEmail += "<br>"
        msgOfEmail += "<p>감사합니다.<p>"
        msgOfEmail += "</div>"

        it.setText(msgOfEmail, "UTF-8", "html")
    }

    fun sendMail(email: String, code: String) = javaMailSender.send(createMail(email, code))
}
