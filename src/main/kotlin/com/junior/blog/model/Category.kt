package com.junior.blog.model

import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Entity
@Table(name = "category")
data class Category(
        @field: NotBlank(message = "{NotBlank.category.title}")
        @field: Size(max = 25, message = "{Size.category.title}")
        var title: String? = null
) : Base() {
        
        @OneToMany(mappedBy = "category", cascade = [(CascadeType.ALL)], fetch = FetchType.EAGER)
        var posts: MutableSet<Post> = mutableSetOf()
}
