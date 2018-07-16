package com.junior.blog.controller

import com.junior.blog.model.Category
import com.junior.blog.service.CategoryServiceImpl
import org.springframework.stereotype.Controller
import org.springframework.transaction.annotation.Transactional
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/category")
class CategoryController(private val categoryService: CategoryServiceImpl) {

    @PostMapping
    fun createCategory(category: Category) : String {
        categoryService.save(category)

        return "redirect:/post/new/form"
    }
    
    @Transactional
    @GetMapping("{id}")
    fun postsFromCategory(@PathVariable id: Long, model: Model) : String {
        val posts = categoryService.getSortedPosts(id)
        
        model.addAttribute("posts", posts)
        model.addAttribute("categories", categoryService.getAll())

        return "index"
    }
}
