package com.junior.blog.model.wrappers

import com.junior.blog.model.User

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

class EmailWrap {
    @field: NotBlank(message = "{NotBlank.user.email}")
    @field: Email(message = "{Email.user.email}")
    var email: String = ""
}

class PassWrap {
    var oldPassword: String = ""
    
    @field: NotBlank(message = "{NotBlank.passWrap.newPassword}")
    @field: Size(min = 3, message = "{Size.passWrap.newPassword}")
    var newPassword: String = ""
    
    fun checkOld(user: User, bindRes: BindingResult) {
        val isPassEquals = BCryptPasswordEncoder(8).matches(oldPassword, user.password)
        
        if ( !isPassEquals )
            bindRes.addError(FieldError("passWrap", "oldPassword", "Старый пароль не корректный"))
    }
}
