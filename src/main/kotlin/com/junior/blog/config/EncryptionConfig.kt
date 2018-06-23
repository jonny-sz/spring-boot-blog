package com.junior.blog.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
class EncryptionConfig {
    @Bean
    fun getPasswordEncoder() = BCryptPasswordEncoder(8)
}
