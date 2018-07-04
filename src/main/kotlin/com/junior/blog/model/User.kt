package com.junior.blog.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "usr")
class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "usr_id")
        val id: Long? = null,
        
        @Column(name = "usr_name")
        private var username: String = "",
        
        @Column(name = "usr_password")
        private var password: String = "",
        
        @Column(name = "usr_email")
        var email: String = "",
        
        @Column(name = "usr_activation_code")
        var activationCode: String? = null,
        
        @Column(name = "usr_created")
        val created: Date = Date(),
        
        @Column(name = "usr_updated")
        var updated: Date = Date(),
        
        @OneToMany(mappedBy = "user", cascade = [(CascadeType.ALL)], fetch = FetchType.EAGER)
        var posts: MutableSet<Post> = mutableSetOf(),
        
        @ElementCollection(targetClass = Role::class, fetch = FetchType.EAGER)
        @CollectionTable(name = "user_role", joinColumns = [(JoinColumn(name = "usr_id"))])
        @Enumerated(EnumType.STRING)
        var roles: MutableSet<Role> = mutableSetOf()
) : UserDetails {
    
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
