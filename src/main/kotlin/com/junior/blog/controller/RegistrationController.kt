package com.junior.blog.controller

import com.junior.blog.controller.util.isErrors
import com.junior.blog.model.User
import com.junior.blog.service.UserServiceImpl
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import javax.validation.Valid

@Controller
class RegistrationController(
        private val userService: UserServiceImpl
) {
    @GetMapping("/registration")
    fun registrationForm() = "registration"

    @PostMapping("/registration")
    fun registration(@Valid user: User, bindRes: BindingResult, model: Model): String {
        if (isErrors(bindRes, model, user)) {
            return "registration"
        }
        if (userService.addUser(user, model)) {
            return "redirect:/login"
        }
        
        return "registration"
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
