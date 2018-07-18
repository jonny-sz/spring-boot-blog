package com.junior.blog.controller

import com.junior.blog.model.Category
import com.junior.blog.service.CategoryServiceImpl
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/category")
class CategoryController(private val categoryService: CategoryServiceImpl) {

    @PostMapping
    fun createCategory(category: Category) : String {
        categoryService.save(category)

        return "redirect:/post/new/form"
    }
    
    @GetMapping("{id}")
    fun postsFromCategory(@PathVariable("id") category: Category, model: Model) : String {
        val posts = category.posts.sortedByDescending { it.created }
        
        model.addAttribute("posts", posts)
        model.addAttribute("categories", categoryService.getAll())

        return "index"
    }
}
