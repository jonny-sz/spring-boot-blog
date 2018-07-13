package com.junior.blog.service

import com.junior.blog.model.Post
import com.junior.blog.model.User
import com.junior.blog.repository.PostRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class PostServiceImpl(private val postRepo: PostRepository) : PostService {

    override fun getAll() = postRepo.findAllByOrderByCreatedDesc()

    override fun save(post: Post): Post = postRepo.save(post)

    override fun getById(id: Long): Optional<Post> = postRepo.findById(id)
    
    override fun getByUser(userId: Long): List<Post> = postRepo.findALLByUser(userId)
    
    override fun deleteSelected(posts: Iterable<Post>) {
        postRepo.deleteInBatch(posts)
    }

    override fun delete(id: Long) {
        postRepo.deleteById(id)
    }
    
    override fun editPost(id: Long, post: Post): Post {
        val postFromDB = getById(id).get()
        
        postFromDB.title = post.title
        postFromDB.description = post.description
        postFromDB.text = post.text
        postFromDB.category = post.category
        
        return postFromDB
    }
    
    override fun createOrUpdate(id: Long?, post: Post, user: User) {
        if (id == null) {
            post.user = user
            save(post)
        } else {
            save(editPost(id, post))
        }
    }
}
