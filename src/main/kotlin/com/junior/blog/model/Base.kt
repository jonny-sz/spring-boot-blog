package com.junior.blog.model

import java.io.Serializable
import java.util.*
import javax.persistence.*

@MappedSuperclass
abstract class Base : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
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
