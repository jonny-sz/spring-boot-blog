package com.junior.blog.controller.util

import org.springframework.validation.BindingResult

fun getErrorsMap(bindRes: BindingResult, objectName: String): Map<String, List<String>> {
    return bindRes.fieldErrors
            .filter { it.objectName == objectName }
            .map { it.field }
            .distinct()
            .map { field ->
                "$objectName${field.capitalize()}Error" to bindRes.fieldErrors
                        .filter { it.field == field }
                        .map { "${it.defaultMessage}" }
                        .sortedBy { it.length }
            }
            .toMap()
}
