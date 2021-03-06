package com.junior.blog.service

import com.junior.blog.model.Role
import com.junior.blog.model.User
import com.junior.blog.model.wrappers.EmailWrap
import com.junior.blog.repository.UserRepository
import com.junior.blog.service.util.activationMsg
import com.junior.blog.service.util.userAlreadyExists
import com.junior.blog.service.util.userNotFound
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.Sort
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.ui.Model
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import java.util.*

@Service
class UserServiceImpl(
        private val userRepo: UserRepository,
        private val passwordEncoder: PasswordEncoder,
        private val mailSender: MailSender
) : UserService, UserDetailsService {
    
    @Value("\${hostname}")
    val hostname: String? = null
    
    @Throws(Exception::class)
    override fun loadUserByUsername(username: String?): UserDetails {
        val user = userRepo.findByUsername(username!!) ?: throw UsernameNotFoundException(userNotFound)
        
        if ( user.activationCode != null ) {
            throw RuntimeException("пользователь не активирован!")
        }
        
        return user
    }
    
    override fun save(user: User) = userRepo.save(user)
    
    override fun getById(id: Long): Optional<User> = userRepo.findById(id)
    
    override fun getByName(username: String) = userRepo.findByUsername(username)
    
    override fun getAll(): List<User> = userRepo.findAll(Sort(Sort.Direction.ASC, "username"))
    
    override fun delete(user: User) {
        userRepo.delete(user)
    }
    
    override fun addUser(user: User, model: Model): Boolean {
        val userFromDB = getByName(user.username)
        
        if (userFromDB != null) {
            model.addAttribute("message", userAlreadyExists)
            
            return false
        }
        
        user.password = passwordEncoder.encode(user.password)
        user.roles = mutableSetOf(Role.USER)
        user.activationCode = UUID.randomUUID().toString()
        
        save(user)
        sendCodeByEmail(user)
        
        return true
    }
    
    private fun sendCodeByEmail(user: User) {
        with(mailSender) {
            val mailSubject = "Activation code"
            val msg = activationMsg.format(
                    user.username,
                    hostname,
                    user.activationCode
            )
        
            send(user.email, mailSubject, msg)
        }
    }
    
    override fun getUsersWithoutMe(): Iterable<User> {
        val auth = SecurityContextHolder.getContext().authentication
        val currentUsername = auth.name
        
        return getAll()
                .filter { it.username != "admin" && it.username != currentUsername }
    }
    
    override fun changeRoles(user: User, form: Map<String, String>) {
        val newRoles = form
                .filterValues { it == "on" }
                .map { it.key }
        
        user.roles.clear()
        newRoles.forEach { user.roles.add(Role.valueOf(it)) }
        save(user)
    }
    
    override fun updatePassword(user: User, newPassword: String, redirect: RedirectAttributes) {
        user.password = passwordEncoder.encode(newPassword)
        save(user)
        redirect.addFlashAttribute("passMessage", "Пароль изменён")
    }
    
    override fun updateEmail(user: User, newEmail: EmailWrap) {
        user.email = newEmail.email
        user.activationCode = UUID.randomUUID().toString()
        save(user)
        sendCodeByEmail(user)
    }
    
    override fun activateUser(code: String): Boolean {
        val user = userRepo.findByActivationCode(code) ?: return false
        
        user.activationCode = null
        save(user)
        
        return true
    }
}
