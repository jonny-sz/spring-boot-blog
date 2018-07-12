package com.junior.blog.service

import com.junior.blog.model.Post
import java.util.*

interface PostService {
    fun getAll(): List<Post>
    fun save(post: Post): Post
    fun getById(id: Long): Optional<Post>
    fun getByUser(userId: Long): List<Post>
    fun deleteSelected(posts: Iterable<Post>)
    fun delete(id: Long)
    fun editPost(id: Long, post: Post): Post
}
