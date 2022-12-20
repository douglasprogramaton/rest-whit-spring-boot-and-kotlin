package com.kotlinstping.advanced.model

import jakarta.persistence.*
import org.hibernate.Hibernate

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

) {
     override fun equals(other: Any?): Boolean {
          if (this === other) return true
          if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
          other as Person

          return id != null && id == other.id
     }

     override fun hashCode(): Int = javaClass.hashCode()
}
