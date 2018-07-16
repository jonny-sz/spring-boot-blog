package com.junior.blog.controller

import com.junior.blog.controller.util.getErrorsMap
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
    fun postForm() = "add-post"
    
    @PostMapping("new/add")
    fun createPost(@AuthenticationPrincipal user: User,
                   @RequestParam(required = false) id: Long?,
                   @Valid post: Post,
                   bindRes: BindingResult,
                   model: Model
    ): String {
        if (bindRes.hasErrors()) {
            model.mergeAttributes(getErrorsMap(bindRes, "post"))
            model.addAttribute("post", post)
            
            return "add-post"
        }
        
        postService.createOrUpdate(id, post, user)
        categoryService.deleteAll(categoryService.getAllEmpty())
        
        return "redirect:/post/${post.id}"
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
        val post = postService.getById(postId).get()
        postService.deleteSelected(listOf(post))
        
        return "redirect:/post/all-my"
    }
    
    @PostMapping("edit-form")
    fun editForm(@RequestParam postId: Long, model: Model): String {
        model.addAttribute("post", postService.getById(postId).get())
        
        return "add-post"
    }
    
    @ModelAttribute
    fun addAttributes(model: Model) {
        model.addAttribute("categories", categoryService.getAll())
    }
}
