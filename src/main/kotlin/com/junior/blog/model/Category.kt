package com.junior.blog.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonView
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Entity
@Table(name = "category")
class Category (
        @field: NotBlank(message = "{NotBlank.category.title}")
        @field: Size(max = 25, message = "{Size.category.title}")
        @JsonView(Views.Public::class)
        var title: String? = null
) : Base() {
        
        @OneToMany(mappedBy = "category", cascade = [(CascadeType.ALL)], fetch = FetchType.LAZY)
        @JsonIgnore
        var posts: MutableSet<Post> = mutableSetOf()
}
