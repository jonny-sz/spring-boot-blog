package com.junior.blog.service

val activationMsg = """
            Hello, %s!
            Welcome to Jonny's Blog.
            For activation your account, please, visit next link:
            http://localhost:8090/activate/%s
        """.trimIndent()

const val userAlreadyExists = "Пользователь с таким именем уже зарегистрирован"

const val userNotFound = "Не найден пользователь с таким именем"
