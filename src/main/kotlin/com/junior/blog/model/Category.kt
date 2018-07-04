package com.junior.blog.model

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "category")
class Category(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "category_id")
        val id: Long? = null,
        
        @Column(name = "category_title")
        var title: String = "",
        
        @Column(name = "category_created")
        val created: Date = Date(),
        
        @Column(name = "category_updated")
        var updated: Date = Date(),
        
        @OneToMany(mappedBy = "category", cascade = [(CascadeType.ALL)], fetch = FetchType.EAGER)
        var posts: MutableSet<Post> = mutableSetOf()
)
