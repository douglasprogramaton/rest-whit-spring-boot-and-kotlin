package com.kotlinstping.advanced.recurso


import com.kotlinstping.advanced.data.vo.v1.PersonVO
import com.kotlinstping.advanced.mapper.DozerMapper
import com.kotlinstping.advanced.model.Person
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.jupiter.api.BeforeEach


class DozerMapperTest {

    var inputObject: MockPerson? = null

    @BeforeEach
    fun setUp() {
        inputObject = MockPerson()
    }

    @Test
    fun parseEntityToVOTest() {
        val output: PersonVO = DozerMapper.parseObject(inputObject!!.mockEntity(), PersonVO::class.java)
        assertEquals(0, output.key)
        assertEquals("First Name Test0", output.firstName)
        assertEquals("Last Name Test0", output.lastName)
        assertEquals("Address Test0", output.address)
        assertEquals("Male", output.gernde)
    }

    @Test
    fun parseEntityListToVOListTest() {
        val outputList: ArrayList<PersonVO> =
            DozerMapper.parseListObjects(inputObject!!.mockEntityList(), PersonVO::class.java)

        val outputZero: PersonVO = outputList[0]

        assertEquals(0, outputZero.key)
        assertEquals("First Name Test3", outputZero.firstName)
        assertEquals("Last Name Test3", outputZero.lastName)
        assertEquals("Address Test3", outputZero.address)
        assertEquals("Male", outputZero.gernde)

        val outputSeven: PersonVO = outputList[7]
        assertEquals(7.toLong(), outputSeven.key)
        assertEquals("First Name Test4", outputSeven.firstName)
        assertEquals("Last Name Test4", outputSeven.lastName)
        assertEquals("Address Test4", outputSeven.address)
        assertEquals("Female", outputSeven.gernde )

        val outputTwelve: PersonVO = outputList[12]
        assertEquals(12.toLong(), outputTwelve.key)
        assertEquals("First Name Test4", outputTwelve.firstName)
        assertEquals("Last Name Test4", outputTwelve.lastName)
        assertEquals("Address Test4", outputTwelve.address)
        assertEquals("Male", outputTwelve.gernde)
    }

    @Test
    fun parseVOToEntityTest() {

        val output: Person = DozerMapper.parseObject(inputObject!!.mockVO(), Person::class.java)

        assertEquals(0, output.id)
        assertEquals("First Name Test0", output.firstName)
        assertEquals("Last Name Test0", output.lastName)
        assertEquals("Address Test0", output.address)
        assertEquals("Male", output.gernde)
    }

    @Test
    fun parserVOListToEntityListTest() {

        val outputList: ArrayList<Person> = DozerMapper.parseListObjects(inputObject!!.mockVOList(), Person::class.java)

        val outputZero: Person = outputList[0]
        assertEquals(0, outputZero.id)
        assertEquals("First Name Test0", outputZero.firstName)
        assertEquals("Last Name Test0", outputZero.lastName)
        assertEquals("Address Test0", outputZero.address)
        assertEquals("Male", outputZero.gernde)

        val outputSeven: Person = outputList[7]
        assertEquals(7, outputSeven.id)
        assertEquals("First Name Test1", outputSeven.firstName)
        assertEquals("Last Name Test1", outputSeven.lastName)
        assertEquals("Address Test1", outputSeven.address)
        assertEquals("Female", outputSeven.gernde)

        val outputTwelve: Person = outputList[12]
        assertEquals(12, outputTwelve.id)
        assertEquals("First Name Test2", outputTwelve.firstName)
        assertEquals("Last Name Test2", outputTwelve.lastName)
        assertEquals("Address Test2", outputTwelve.address)
        assertEquals("Male", outputTwelve.gernde)
    }
}