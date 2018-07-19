package com.junior.blog.controller.api

import com.fasterxml.jackson.annotation.JsonView
import com.junior.blog.model.Post
import com.junior.blog.model.Views
import com.junior.blog.service.PostServiceImpl
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/post")
class RestPostController(private val postService: PostServiceImpl) {
    
    @JsonView(Views.WithDate::class)
    @GetMapping
    fun getAll() = postService.getAll()
    
    @JsonView(Views.WithDate::class)
    @GetMapping("{id}")
    fun getOne(@PathVariable("id") id: Long): ResponseEntity<Post> = postService.getById(id)
            .map { post -> ResponseEntity.ok(post) }
            .orElse(ResponseEntity.notFound().build())
}
