package com.junior.blog.controller

import com.junior.blog.controller.util.getErrors
import com.junior.blog.model.Post
import com.junior.blog.model.User
import com.junior.blog.service.CategoryServiceImpl
import com.junior.blog.service.PostServiceImpl
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@Controller
@ControllerAdvice
@RequestMapping("/post")
class PostController(
        private val postService: PostServiceImpl,
        private val categoryService: CategoryServiceImpl
) {
    @GetMapping("new/form")
    fun postForm(model: Model) = "add-post"
    
    @PostMapping("new/add")
    fun createPost(@AuthenticationPrincipal user: User,
                   @Valid post: Post,
                   bindRes: BindingResult,
                   model: Model
    ): String {
        if (bindRes.hasErrors()) {
            model.mergeAttributes(getErrors(bindRes))
            model.addAttribute("post", post)
            
            return "add-post"
        }
        
        post.user = user
        postService.save(post)
        categoryService.deleteAll(categoryService.getAllEmpty())
        
        return "redirect:/"
    }
    
    @GetMapping("{post}")
    fun postView(@PathVariable post: Post, model: Model): String {
        model.addAttribute("post", post)
        
        return "post"
    }
    
    @GetMapping("all-my")
    @PreAuthorize("hasAuthority('USER')")
    fun getUserPosts(@AuthenticationPrincipal authUser: User, model: Model): String {
        model.addAttribute("posts", postService.getByUser(authUser.id!!))
        
        return "my-posts"
    }
    
    @PostMapping("delete")
    fun delPost(@RequestParam postId: Long): String {
        val post = postService.getById(postId)
        postService.deleteSelected(listOf(post))
        
        return "redirect:/post/all-my"
    }
    
    @PostMapping("edit-form")
    fun editForm(@RequestParam postId: Long, model: Model): String {
        model.addAttribute("post", postService.getById(postId))
        
        return "edit-post"
    }
    
    @PostMapping("edit")
    fun editPost(@RequestParam("id") postFromDB: Post,
                 updatedPost: Post
    ): String {
        postService.compareAndMerge(postFromDB, updatedPost)
        postService.save(postFromDB)
        
        return "redirect:/post/${postFromDB.id}"
    }
    
    
    @ModelAttribute
    fun addAttributes(model: Model) {
        model.addAttribute("categories", categoryService.getAll())
    }
}
