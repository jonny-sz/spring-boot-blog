package com.junior.blog.controller.util

import com.junior.blog.model.Base
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import kotlin.reflect.full.primaryConstructor

fun <T : Base> isErrors(bindRes: BindingResult, model: Model, entity: T): Boolean {
    if (bindRes.hasErrors()) {
        val attrName = entity::class.simpleName!!.toLowerCase()
        val fieldList = entity::class.primaryConstructor!!.parameters.map { it.name }
        val modelMap = fieldList
                .map { field -> "$attrName${field!!.capitalize()}Error" to bindRes.fieldErrors
                        .filter { it.field == field }
                        .map { "${it.defaultMessage}" }
                        .sortedBy { it.length } }
                .toMap()
        
        model.mergeAttributes(modelMap)
        model.addAttribute(attrName, entity)
        
        return true
    }
    return false
}
