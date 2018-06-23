package com.junior.blog

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootBlogApplication

fun main(args: Array<String>) {
    runApplication<SpringBootBlogApplication>(*args)
}
