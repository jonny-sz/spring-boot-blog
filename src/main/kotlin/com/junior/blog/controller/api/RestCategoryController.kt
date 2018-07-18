package com.junior.blog.controller.api

import com.fasterxml.jackson.annotation.JsonView
import com.junior.blog.controller.util.getErrorsMap
import com.junior.blog.model.Category
import com.junior.blog.model.Post
import com.junior.blog.model.Views
import com.junior.blog.service.CategoryServiceImpl
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/category")
class RestCategoryController(private val categoryService: CategoryServiceImpl) {
    
    @JsonView(Views.Public::class)
    @GetMapping
    fun getAll() = categoryService.getAll()
    
    @JsonView(Views.Public::class)
    @PostMapping
    fun createNew(@Valid @RequestBody category: Category, bindRes: BindingResult): ResponseEntity<in Any> {
        if ( bindRes.hasErrors() ) {
            return ResponseEntity.badRequest().body(getErrorsMap(bindRes, "category"))
        }
        return ResponseEntity.ok(categoryService.save(category))
    }
    
    @JsonView(Views.Public::class)
    @GetMapping("{id}")
    fun getOne(@PathVariable id: Long): ResponseEntity<Category> = categoryService.getById(id)
            .map { category -> ResponseEntity.ok(category) }
            .orElse(ResponseEntity.notFound().build())
    
    @JsonView(Views.WithDate::class)
    @GetMapping("{id}/posts")
    fun getPostsById(@PathVariable id: Long): ResponseEntity<MutableSet<Post>> = categoryService.getById(id)
            .map { category -> ResponseEntity.ok(category.posts) }
            .orElse(ResponseEntity.notFound().build())
}
