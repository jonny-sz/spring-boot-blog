package com.junior.blog.controller.api

import com.junior.blog.controller.util.getErrorsMap
import com.junior.blog.model.Category
import com.junior.blog.service.CategoryServiceImpl
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
@RestController
@RequestMapping("/api/category")
class RestCategoryController(private val categoryService: CategoryServiceImpl) {
    
    @GetMapping
    fun getAll() = categoryService.getAll()
    
    @PostMapping
    fun createNew(@Valid @RequestBody category: Category, bindRes: BindingResult): ResponseEntity<in Any> {
        if ( bindRes.hasErrors() ) {
            return ResponseEntity.badRequest().body(getErrorsMap(bindRes, "category"))
        }
        return ResponseEntity.ok(categoryService.save(category))
    }
    
    @GetMapping("{id}")
    fun getOne(@PathVariable id: Long): ResponseEntity<Category> = categoryService.getById(id)
            .map { category -> ResponseEntity.ok(category) }
            .orElse(ResponseEntity.notFound().build())
    
}

