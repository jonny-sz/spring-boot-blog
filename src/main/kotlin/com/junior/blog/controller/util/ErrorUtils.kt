package com.junior.blog.controller.util

import org.springframework.validation.BindingResult

fun getErrors(bindRes: BindingResult) = bindRes.fieldErrors
        .map { "${it.field}Error" to it.defaultMessage }
        .toMap()
