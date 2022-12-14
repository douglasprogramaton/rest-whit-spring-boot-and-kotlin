package com.kotlinstping.douglas.repository

import com.kotlinstping.douglas.Model.Person
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonRepository :JpaRepository<Person,Long> {
}