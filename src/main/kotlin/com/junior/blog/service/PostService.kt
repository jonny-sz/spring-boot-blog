package com.junior.blog.service

import com.junior.blog.model.Post
import com.junior.blog.model.User

interface PostService {
    fun getAll(): List<Post>
    fun save(post: Post): Post
    fun getById(id: Long): Post
    fun getByUser(userId: Long): List<Post>
    fun deleteSelected(posts: Iterable<Post>)
    fun delete(post: Post)
    fun compareAndMerge(postFromDB: Post, updatedPost: Post)
}
