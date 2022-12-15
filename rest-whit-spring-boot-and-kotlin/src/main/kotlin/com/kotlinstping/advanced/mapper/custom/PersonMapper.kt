package com.kotlinstping.advanced.mapper.custom

import com.kotlinstping.advanced.Model.Person
import com.kotlinstping.advanced.data.vo.v2.PersonVO
import org.springframework.stereotype.Service
import java.util.*

@Service
class PersonMapper {

    fun mapEntityToVO(person: Person): PersonVO {
        val vo = PersonVO()
        vo.id = person.id
        vo.address = person.address
        vo.birthDay = Date()
        vo.firstName = person.firstName
        vo.lastName = person.lastName
        vo.gernde = person.gernde
        return vo
    }

    fun mapVOToEntity(person: PersonVO): Person {
        val entity = Person()
        entity.id = person.id
        entity.address = person.address
        // entity.birthDay = person.birthDay
        entity.firstName = person.firstName
        entity.lastName = person.lastName
        entity.gernde = person.gernde
        return entity
    }
}
