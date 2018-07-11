package com.junior.blog.config

import com.junior.blog.service.UserServiceImpl
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.password.PasswordEncoder


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebSecurityConfig(
        private val userService: UserServiceImpl,
        private val passwordEncoder: PasswordEncoder
) : WebSecurityConfigurerAdapter() {
    
    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http
                .authorizeRequests()
                    .antMatchers(
                            "/css/**",
                            "/js/**",
                            "/img/**",
                            "/",
                            "/post/*",
                            "/category/**",
                            "/registration",
                            "/activate/*"
                    ).permitAll()
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .permitAll()
                .and()
                    .rememberMe()
                .and()
                    .logout()
                    .permitAll()
        
        http.headers().xssProtection()
    }

    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userService)
                .passwordEncoder(passwordEncoder)
    }
}
