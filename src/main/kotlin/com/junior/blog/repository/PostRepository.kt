package com.junior.blog.repository

import com.junior.blog.model.Post
import com.junior.blog.repository.util.selectAllPostsByUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface PostRepository : JpaRepository<Post, Long> {
    fun findAllByOrderByCreatedDesc(): List<Post>
    
    @Query(selectAllPostsByUser, nativeQuery = true)
    fun findALLByUser(userId: Long): List<Post>
}
