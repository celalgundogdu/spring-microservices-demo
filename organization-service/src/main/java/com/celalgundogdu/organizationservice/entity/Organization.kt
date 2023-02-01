package com.celalgundogdu.organizationservice.entity

import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "organizations")
data class Organization @JvmOverloads constructor(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = 0,

    @Column(nullable = false)
    val organizationName: String,

    val organizationDescription: String? = "",

    @Column(nullable = false)
    val organizationCode: String,

    @CreationTimestamp
    val createdDate: LocalDateTime? = null
)
