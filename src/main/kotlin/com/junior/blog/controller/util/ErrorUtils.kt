package com.junior.blog.controller.util

import org.springframework.validation.BindingResult

fun getErrors(bindRes: BindingResult) = bindRes.fieldErrors
        .map { "${it.objectName}${it.field.capitalize()}Error" to it.defaultMessage }
        .toMap()
