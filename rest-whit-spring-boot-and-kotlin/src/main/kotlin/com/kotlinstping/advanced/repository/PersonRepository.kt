package com.kotlinstping.advanced.repository

import com.kotlinstping.advanced.model.Person
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonRepository :JpaRepository<Person,Long> {
}