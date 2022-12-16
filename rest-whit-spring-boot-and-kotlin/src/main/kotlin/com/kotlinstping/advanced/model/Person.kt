package com.kotlinstping.advanced.model

import jakarta.persistence.*

@Entity(name="person")
data class Person(
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     var id :Long=0,
     @Column
     var firstName: String="",
     @Column
     var lastName: String="",
     @Column
     var address: String="",
     @Column
     var gernde: String=""

)
