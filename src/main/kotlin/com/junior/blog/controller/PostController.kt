package com.junior.blog.controller

import com.junior.blog.model.Post
import com.junior.blog.model.User
import com.junior.blog.service.CategoryServiceImpl
import com.junior.blog.service.PostServiceImpl
import com.junior.blog.service.UserServiceImpl
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@ControllerAdvice
@RequestMapping("/post")
class PostController(
        private val postService: PostServiceImpl,
        private val userService: UserServiceImpl,
        private val categoryService: CategoryServiceImpl
) {
    @GetMapping("new/form")
    fun postForm(model: Model) = "add-post"
    
    @PostMapping("new/add")
    fun createPost(post: Post,
                   @AuthenticationPrincipal user: User): String {
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
        val userFromDB = userService.getByName(authUser.username)
        val sortedPosts = userFromDB!!.posts.sortedByDescending { it.created }
        model.addAttribute("posts", sortedPosts)
        
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
        val post = postService.getById(postId)
        model.addAttribute("post", post)
        
        return "edit-post"
    }
    
    @PostMapping("edit")
    fun editPost(@RequestParam("id") postFromDB: Post,
                 updatedPost: Post
    ): String {
        postService.compareAndMerge(postFromDB, updatedPost)
        postService.save(postFromDB)
        
        return "redirect:/post/all-my"
    }
    
    
    @ModelAttribute
    fun addAttributes(model: Model) {
        model.addAttribute("categories", categoryService.getAll())
    }
}
