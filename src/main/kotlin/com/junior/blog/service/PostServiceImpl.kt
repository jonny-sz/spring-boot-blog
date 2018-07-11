package com.junior.blog.service

import com.junior.blog.model.Post
import com.junior.blog.model.User
import com.junior.blog.repository.PostRepository
import org.springframework.stereotype.Service

@Service
class PostServiceImpl(private val postRepo: PostRepository) : PostService {

    override fun getAll() = postRepo.findAllByOrderByCreatedDesc()

    override fun save(post: Post): Post = postRepo.save(post)

    override fun getById(id: Long): Post = postRepo.getOne(id)
    
    override fun getByUser(userId: Long): List<Post> = postRepo.findALLByUser(userId)
    
    override fun deleteSelected(posts: Iterable<Post>) {
        postRepo.deleteInBatch(posts)
    }

    override fun delete(post: Post) {
        postRepo.delete(post)
    }
    
    override fun editPost(id: Long, post: Post): Post {
        val postFromDB = getById(id)
        
        postFromDB.title = post.title
        postFromDB.description = post.description
        postFromDB.text = post.text
        postFromDB.category = post.category
        
        return postFromDB
    }
}
