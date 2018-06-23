package com.junior.blog.service

import com.junior.blog.model.User
import org.springframework.ui.Model
import org.springframework.web.servlet.mvc.support.RedirectAttributes

interface UserService {
    fun save(user: User) : User
    fun getByName(username: String) : User?
    fun getAll() : List<User>
    fun delete(user: User)
    fun addUser(user: User, model: Model): Boolean
    fun getUsersWithoutMe(): Iterable<User>
    fun changeRoles(user: User, form: Map<String, String>)
    fun updatePassword(user: User, newPassword: String, oldPassword: String, redirectAttr: RedirectAttributes)
    fun updateEmail(user: User, newEmail: String, redirectAttr: RedirectAttributes)
    fun activateUser(code: String): Boolean
}
