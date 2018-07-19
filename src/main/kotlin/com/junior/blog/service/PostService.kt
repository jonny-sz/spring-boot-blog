package com.junior.blog.service

import com.junior.blog.model.Post
import com.junior.blog.model.User
import java.util.*

interface PostService {
    fun getAll(): List<Post>
    fun save(post: Post): Post
    fun getById(id: Long): Optional<Post>
    fun deleteAll(posts: Iterable<Post>)
    fun delete(post: Post)
    fun editPost(id: Long, post: Post): Post
    fun createOrUpdate(id: Long?, post: Post, user: User)
}
