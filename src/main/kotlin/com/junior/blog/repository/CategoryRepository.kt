package com.junior.blog.repository

import com.junior.blog.model.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepository : JpaRepository<Category, Long> {
    fun findByTitle(title: String) : Category

    @Query(selectEmptyCategories, nativeQuery = true)
    fun findAllEmpty() : List<Category>
}
