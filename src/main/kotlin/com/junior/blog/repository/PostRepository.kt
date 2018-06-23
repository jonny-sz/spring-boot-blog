package com.junior.blog.repository

import com.junior.blog.model.Post
import com.junior.blog.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PostRepository : JpaRepository<Post, Long> {
    fun findAllByOrderByCreatedDesc(): List<Post>
    fun findByUser(user: User): List<Post>
}
