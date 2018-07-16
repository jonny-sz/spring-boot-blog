package com.junior.blog.model

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Entity
@Table(name = "category")
class Category(
        @field: NotBlank(message = "{NotBlank.category.title}")
        @field: Size(max = 25, message = "{Size.category.title}")
        var title: String? = null
) : Base() {
        
        @JsonIgnore
        @OneToMany(mappedBy = "category", cascade = [(CascadeType.ALL)], fetch = FetchType.LAZY)
        var posts: MutableSet<Post> = mutableSetOf()
}
