package com.junior.blog.model

import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@Table(name = "post")
class Post(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "post_id")
        val id: Long? = null,
        
        @Column(name = "post_title")
        @field:NotBlank(message = "{NotBlank.post.title}")
        @field:Size(max = 255, message = "{Size.post.title}")
        var title: String? = null,

        @Column(name = "post_description")
        @field:NotBlank(message = "{NotBlank.post.description}")
        @field:Size(max = 255, message = "{Size.post.description}")
        var description: String? = null,

        @Column(name = "post_text")
        @field:NotBlank(message = "{NotBlank.post.text}")
        var text: String? = null,

        @Column(name = "post_created")
        val created: Date = Date(),

        @Column(name = "post_updated")
        var updated: Date = Date(),

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "category_id")
        @field:NotNull(message = "{NotNull.post.category}")
        var category: Category? = null,

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "usr_id")
        var user: User? = null
)
