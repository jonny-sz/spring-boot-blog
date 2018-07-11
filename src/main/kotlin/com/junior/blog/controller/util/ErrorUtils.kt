package com.junior.blog.controller.util

import com.junior.blog.model.Base
import org.springframework.ui.Model
import org.springframework.validation.BindingResult

fun <T : Base> isErrors(bindRes: BindingResult, model: Model, entity: T): Boolean {
    if (bindRes.hasErrors()) {
        val attrName = entity::class.simpleName!!.toLowerCase()
    
        model.mergeAttributes(getErrorsMap(bindRes, attrName))
        model.addAttribute(attrName, entity)
        
        return true
    }
    return false
}

private fun getErrorsMap(bindRes: BindingResult, objectName: String): Map<String, List<String>> {
    return bindRes.fieldErrors
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
