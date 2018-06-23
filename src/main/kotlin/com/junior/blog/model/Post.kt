package com.junior.blog.model

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "post")
class Post(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "post_id")
        val id: Long? = null,

        @Column(name = "post_title")
        var title: String = "",

        @Column(name = "post_description")
        var description: String = "",

        @Column(name = "post_text")
        var text: String = "",

        @Column(name = "post_created")
        val created: Date = Date(),

        @Column(name = "post_updated")
        var updated: Date = Date(),

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "category_id")
        var category: Category? = null,

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "usr_id")
        var user: User? = null
)
