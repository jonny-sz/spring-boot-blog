package com.junior.blog.service

import com.junior.blog.model.Category
import java.util.*

interface CategoryService {
    fun getAll() : List<Category>
    fun getAllEmpty() : List<Category>
    fun getById(id: Long) : Optional<Category>
    fun getByTitle(title: String) : Category
    fun save(category: Category) : Category
    fun deleteAll(categories: List<Category>)
}
