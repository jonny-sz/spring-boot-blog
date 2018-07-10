package com.junior.blog.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Entity
@Table(name = "usr")
class User(
        @field: NotBlank(message = "{NotBlank.user.username}")
        @field: Size(min = 3, max = 20, message = "{Size.user.username}")
        private var username: String = "",
        
        @field: NotBlank(message = "{NotBlank.user.password}")
        @field: Size(min = 3, max = 20, message = "{Size.user.password}")
        private var password: String = "",
        
        @field: NotBlank(message = "{NotBlank.user.email}")
        @field: Email(message = "{Email.user.email}")
        var email: String = ""
) : Base(), UserDetails{
    
    var activationCode: String? = null
    
    @OneToMany(mappedBy = "user", cascade = [(CascadeType.ALL)], fetch = FetchType.EAGER)
    var posts: MutableSet<Post> = mutableSetOf()
    
    @ElementCollection(targetClass = Role::class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = [(JoinColumn(name = "usr_id"))])
    @Enumerated(EnumType.STRING)
    var roles: MutableSet<Role> = mutableSetOf()
    
    fun setPassword(password: String) {
        this.password = password
    }
    
    fun isAdmin() = Role.ADMIN in roles
    
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = roles
    override fun isEnabled(): Boolean = true
    override fun getUsername(): String = username
    override fun getPassword(): String = password
    override fun isCredentialsNonExpired(): Boolean = true
    override fun isAccountNonExpired(): Boolean = true
    override fun isAccountNonLocked(): Boolean = true
}
