package com.junior.blog.model

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonView
import java.io.Serializable
import java.util.*
import javax.persistence.*

@MappedSuperclass
abstract class Base : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.Public::class)
    var id: Long? = null
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    @JsonView(Views.WithDate::class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy, HH:mm:ss")
    var created: Date? = null
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    var updated: Date? = null
    
    @PrePersist
    fun onCreate() {
        created = Date()
        updated = created
    }
    
    @PreUpdate
    fun onUpdate() {
        updated = Date()
    }
}
