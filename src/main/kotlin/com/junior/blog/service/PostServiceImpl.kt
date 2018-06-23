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
    
    override fun getByUser(user: User): List<Post> = postRepo.findByUser(user)
    
    override fun deleteSelected(posts: Iterable<Post>) {
        postRepo.deleteInBatch(posts)
    }

    override fun delete(post: Post) {
        postRepo.delete(post)
    }
    
    override fun compareAndMerge(postFromDB: Post, updatedPost: Post) {
        if ( updatedPost.title.isNotEmpty() )
            postFromDB.title = updatedPost.title
        
        if ( updatedPost.description.isNotEmpty() )
            postFromDB.description = updatedPost.description
        
        if ( updatedPost.text.isNotEmpty() )
            postFromDB.text = updatedPost.text
    }
}
