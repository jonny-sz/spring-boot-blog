package com.junior.blog.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl

@Configuration
class MailConfig {
    @Value("\${spring.mail.host}")
    val mailHost: String? = null
    
    @Value("\${spring.mail.username}")
    val mailUsername: String? = null
    
    @Value("\${spring.mail.password}")
    val mailPassword: String? = null
    
    @Value("\${spring.mail.port}")
    val mailPort: Int? = null
    
    @Value("\${spring.mail.protocol}")
    val mailProtocol: String? = null
    
    @Value("\$(spring.mail.properties.mail.smtp.auth)")
    val mailAuth: String? = null
    
    @Value("\$(spring.mail.properties.mail.smtp.starttls.enable)")
    val mailTlsEn: String? = null
    
    @Value("\$(mail.smtp.ssl.trust)")
    val mailTrust: String? = null
    
    @Value("\${mail.debug}")
    val debug: String? = null
    
    
    @Bean(name = ["myMailSender"])
    fun getMailSender(): JavaMailSender {
        return JavaMailSenderImpl().apply {
            host = mailHost
            port = mailPort!!
            username = mailUsername
            password = mailPassword
            javaMailProperties.setProperty("mail.transport.protocol", mailProtocol)
            javaMailProperties.setProperty("mail.smtp.auth", mailAuth)
            javaMailProperties.setProperty("mail.smtp.starttls.enable", mailTlsEn)
            javaMailProperties.setProperty("mail.smtp.ssl.trust", mailTrust)
            javaMailProperties.setProperty("mail.debug", debug)
        }
    }
}
