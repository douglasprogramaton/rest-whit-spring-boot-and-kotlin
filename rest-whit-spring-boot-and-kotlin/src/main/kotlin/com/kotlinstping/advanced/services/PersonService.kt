package com.kotlinstping.advanced.services

import com.kotlinstping.advanced.controller.PersonController
import com.kotlinstping.advanced.model.Person
import com.kotlinstping.advanced.data.vo.v1.PersonVO
import com.kotlinstping.advanced.exception.RequiredObjectIsNullException
import com.kotlinstping.advanced.data.vo.v2.PersonVO as PersonVOV2
import com.kotlinstping.advanced.exception.ResourceNotFoundException
import com.kotlinstping.advanced.mapper.DozerMapper
import com.kotlinstping.advanced.mapper.custom.PersonMapper
import com.kotlinstping.advanced.repository.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class PersonService {

    @Autowired
    private lateinit var repository: PersonRepository

    @Autowired
    private lateinit var mapper: PersonMapper

    private val logger = Logger.getLogger(PersonService::class.java.name)

    fun findAll(): List<PersonVO> {
        logger.info("Finding all people!")
        val persons = repository.findAll()
       val vos= DozerMapper.parseListObjects(persons, PersonVO::class.java)

        for (person in vos) {
            val withSelfRel= linkTo(PersonController::class.java).slash(person.key).withSelfRel()
            person.add(withSelfRel)
        }
        return vos
    }

    fun findById(id: Long): PersonVO {
        logger.info("Finding one person!")
        var person = repository.findById(id)
            .orElseThrow { ResourceNotFoundException("No records found with ID $id!") }
        val personVO:PersonVO= DozerMapper.parseObject(person, PersonVO::class.java)
        val withSelfRel= linkTo(PersonController::class.java).slash(personVO.key).withSelfRel()
        personVO.add(withSelfRel)
        return personVO
    }

    fun create(person: PersonVO?) : PersonVO{
        if (person==null)throw RequiredObjectIsNullException()
        logger.info("Creating one person with name ${person.firstName}!")
        var entity: Person = DozerMapper.parseObject(person, Person::class.java)
        val personVO:PersonVO=DozerMapper.parseObject(repository.save(entity), PersonVO::class.java)
        val withSelfRel= linkTo(PersonController::class.java).slash(personVO.key).withSelfRel()
        personVO.add(withSelfRel)
        return personVO

    }

    fun update(person: PersonVO?) : PersonVO{
        if (person==null)throw RequiredObjectIsNullException()
        logger.info("Updating one person with ID ${person.key}!")
        val entity = repository.findById(person.key)
            .orElseThrow { ResourceNotFoundException("No records found for this ID!") }

        entity.firstName = person.firstName
        entity.lastName = person.lastName
        entity.address = person.address
        entity.gernde = person.gernde
        val personVO:PersonVO=DozerMapper.parseObject(repository.save(entity), PersonVO::class.java)
        val withSelfRel= linkTo(PersonController::class.java).slash(personVO.key).withSelfRel()
        personVO.add(withSelfRel)
        return personVO

    }

    fun delete(id: Long) {
        logger.info("Deleting one person with ID $id!")
        val entity = repository.findById(id)
            .orElseThrow { ResourceNotFoundException("No records found for this ID!") }
        repository.delete(entity)
    }

}