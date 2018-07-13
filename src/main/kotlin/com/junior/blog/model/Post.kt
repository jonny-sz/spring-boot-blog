package com.junior.blog.model

import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@Table(name = "post")
class Post(
        @field: NotBlank(message = "{NotBlank.post.title}")
        @field: Size(max = 255, message = "{Size.post.title}")
        var title: String? = null,

        @field: NotBlank(message = "{NotBlank.post.description}")
        @field: Size(max = 255, message = "{Size.post.description}")
        var description: String? = null,

        @Column(columnDefinition = "TEXT")
        @field: NotBlank(message = "{NotBlank.post.text}")
        var text: String? = null
) : Base() {
        
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "category_id")
        @field: NotNull(message = "{NotNull.post.category}")
        var category: Category? = null
        
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "usr_id")
        var user: User? = null
}
