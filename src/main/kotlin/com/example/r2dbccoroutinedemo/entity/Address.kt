package com.example.r2dbccoroutinedemo.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("addresses")
data class Address(
    @Id @Column("id") val id: Long,
    @Column("city") val city: String,
    @Column("country") val country: String,
    @Column("street") val street: String,
)