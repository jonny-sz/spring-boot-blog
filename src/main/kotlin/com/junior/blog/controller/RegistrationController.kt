package com.junior.blog.controller

import com.junior.blog.model.User
import com.junior.blog.service.UserServiceImpl
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
class RegistrationController(
        private val userService: UserServiceImpl
) {
    @GetMapping("/registration")
    fun registrationForm() = "registration"

    @PostMapping("/registration")
    fun registration(user: User, model: Model) = when (userService.addUser(user, model)) {
        true -> "redirect:/login"
        false -> "registration"
    }
    
    @GetMapping("/activate/{code}")
    fun activateUser(model: Model, @PathVariable code: String): String {
        val isActivated = userService.activateUser(code)
        
        if (isActivated)
            model.addAttribute("success_activation_message", "Активация прошла успешно!")
        else
            model.addAttribute("fail_activation_message", "Код активации не найден!")
        
        return "login"
    }
}
