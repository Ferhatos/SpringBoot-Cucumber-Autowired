package com.ferobase.demospringbootbatchcuccumber.entities

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class MyEntity(
        @Id
        @GeneratedValue
        var id: Long? = 0L,
        @Column
        var name: String? = "",
        @Column
        var firstName: String? = ""
)