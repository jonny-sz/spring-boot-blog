package com.junior.blog.service.util

import org.springframework.beans.factory.annotation.Value

@Value("\${hostname}")
val hostname: String? = null

val activationMsg = """
            Hello, %s!
            Welcome to Jonny's Blog.
            For activation your account, please, visit next link:
            http://$hostname/activate/%s
        """.trimIndent()

const val userAlreadyExists = "Пользователь с таким именем уже зарегистрирован"

const val userNotFound = "Не найден пользователь с таким именем"
