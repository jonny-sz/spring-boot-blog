package com.junior.blog.service

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class MailSender(
        @Qualifier("myMailSender")
        private val javaMailSender: JavaMailSender
) {
    @Value("\${spring.mail.username}")
    val mailUsername: String? = null
    
    fun send(emailTo: String, subject: String, message: String) {
        javaMailSender.send(SimpleMailMessage().apply {
            setFrom(mailUsername!!)
            setTo(emailTo)
            setSubject(subject)
            setText(message)
        })
    }
}
