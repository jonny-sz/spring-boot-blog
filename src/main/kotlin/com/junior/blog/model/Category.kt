package com.junior.blog.model

import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Entity
@Table(name = "category")
class Category(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "category_id")
        val id: Long? = null,
        
        @Column(name = "category_title")
        @field:NotBlank(message = "Категория не может быть пустой")
        @field:Size(max = 25, message = "название слишком длинное, макс 25")
        var title: String? = null,
        
        @Column(name = "category_created")
        val created: Date = Date(),
        
        @Column(name = "category_updated")
        var updated: Date = Date(),
        
        @OneToMany(mappedBy = "category", cascade = [(CascadeType.ALL)], fetch = FetchType.EAGER)
        var posts: MutableSet<Post> = mutableSetOf()
)
