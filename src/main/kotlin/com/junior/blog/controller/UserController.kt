package com.junior.blog.controller

import com.junior.blog.controller.util.getErrorsMap
import com.junior.blog.model.Role
import com.junior.blog.model.User
import com.junior.blog.model.wrappers.EmailWrap
import com.junior.blog.model.wrappers.PassWrap
import com.junior.blog.service.CategoryServiceImpl
import com.junior.blog.service.PostServiceImpl
import com.junior.blog.service.UserServiceImpl

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes

import javax.validation.Valid

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
                       @Valid passwords: PassWrap,
                       bindRes: BindingResult,
                       redirect: RedirectAttributes
    ): String {
        passwords.checkOld(user, bindRes)
        
        if ( bindRes.hasErrors() ) {
            getErrorsMap(bindRes, "passWrap")
                    .forEach { redirect.addFlashAttribute(it.key, it.value) }
        } else {
            userService.updatePassword(user, passwords.newPassword, redirect)
        }
        
        return "redirect:/user/profile"
    }
    
    @PostMapping("profile/email")
    fun updateEmail(@AuthenticationPrincipal user: User,
                    @Valid newEmail: EmailWrap,
                    bindRes: BindingResult,
                    redirect: RedirectAttributes
    ): String {
        if ( newEmail.email == user.email ) {
            bindRes.addError(FieldError("emailWrap", "email", "Этот email уже активирован"))
        }
        if ( !bindRes.hasErrors() ) {
            userService.updateEmail(user, newEmail)
            SecurityContextHolder.clearContext()
        }
        
        getErrorsMap(bindRes, "emailWrap")
                .forEach { redirect.addFlashAttribute(it.key, it.value) }
        
        return "redirect:/user/profile"
    }
}
