package com.junior.blog.controller

import com.junior.blog.model.Role
import com.junior.blog.model.User
import com.junior.blog.service.CategoryServiceImpl
import com.junior.blog.service.PostServiceImpl
import com.junior.blog.service.UserServiceImpl
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
@RequestMapping("/user")
class UserController(
        private val userService: UserServiceImpl,
        private val categoryService: CategoryServiceImpl,
        private val postService: PostServiceImpl
) {
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    fun showUsers(model: Model): String {
        val users = userService.getUsersWithoutMe()
        
        model.addAttribute("users", users)
        model.addAttribute("categories", categoryService.getAll())
        
        return "users"
    }
    
    @GetMapping("edit/{user}")
    @PreAuthorize("hasAuthority('ADMIN')")
    fun editUser(@PathVariable user: User, model: Model): String {
        model.addAttribute("allRoles", Role.values())
        model.addAttribute("user", user)
        model.addAttribute("categories", categoryService.getAll())
        
        return "edit-user"
    }
    
    @PostMapping("edit/{user}")
    @PreAuthorize("hasAuthority('ADMIN')")
    fun getNewRoles(@PathVariable user: User,
                    @RequestParam form: Map<String, String>
    ): String {
        userService.changeRoles(user, form)
        
        return "redirect:/user"
    }
    
    @GetMapping("delete/{user}")
    @PreAuthorize("hasAuthority('ADMIN')")
    fun deleteUser(@PathVariable user: User): String {
        postService.deleteSelected(user.posts)
        userService.delete(user)
        
        return "redirect:/user"
    }
    
    @GetMapping("profile")
    fun getProfile(@AuthenticationPrincipal user: User,
                   model: Model
    ): String {
        model.addAttribute("categories", categoryService.getAll())
        model.addAttribute("oldEmail", user.email)
        
        return "profile"
    }
    
    @PostMapping("profile/password")
    fun updatePassword(@AuthenticationPrincipal user: User,
                       @RequestParam oldPassword: String,
                       @RequestParam newPassword: String,
                       redirectAttr: RedirectAttributes
    ): String {
        userService.updatePassword(user, newPassword, oldPassword, redirectAttr)
        
        return "redirect:/user/profile"
    }
    
    @PostMapping("profile/email")
    fun updateEmail(@AuthenticationPrincipal user: User,
                    @RequestParam newEmail: String,
                    redirectAttr: RedirectAttributes
    ): String {
        userService.updateEmail(user, newEmail, redirectAttr)
        
        return "redirect:/login"
    }
}
