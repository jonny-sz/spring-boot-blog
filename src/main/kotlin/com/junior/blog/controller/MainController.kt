package com.junior.blog.controller

import com.junior.blog.service.CategoryServiceImpl
import com.junior.blog.service.PostServiceImpl
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class MainController(
        private val postService: PostServiceImpl,
        private val categoryService: CategoryServiceImpl
) {
    @GetMapping("/")
    fun home(model: Model) : String {
        model.addAttribute("posts", postService.getAll())
        model.addAttribute("categories", categoryService.getAll())

        return "index"
    }
}
