package com.kotlinstping.restwhitspringbootandkotlin.repository

import com.kotlinstping.restwhitspringbootandkotlin.Model.Person
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonRepository :JpaRepository<Person,Long> {
}