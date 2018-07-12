package com.junior.blog.service

import com.junior.blog.model.Category
import com.junior.blog.repository.CategoryRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class CategoryServiceImpl(private val categoryRepo: CategoryRepository) : CategoryService {

    override fun getAll(): List<Category> = categoryRepo.findAll()

    override fun getAllEmpty(): List<Category> = categoryRepo.findAllEmpty()
    
    override fun getById(id: Long): Optional<Category> = categoryRepo.findById(id)
    
    override fun getByTitle(title: String): Category = categoryRepo.findByTitle(title)

    override fun save(category: Category): Category = categoryRepo.save(category)

    override fun deleteAll(categories: List<Category>) {
        categoryRepo.deleteAll(categories)
    }
}
