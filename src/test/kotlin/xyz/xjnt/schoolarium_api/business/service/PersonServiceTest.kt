package xyz.xjnt.schoolarium_api.business.service

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertNotNull
import org.junit.jupiter.api.assertThrows
import org.openapitools.jackson.nullable.JsonNullable
import org.springframework.data.repository.findByIdOrNull
import xyz.xjnt.schoolarium_api.business.model.Person
import xyz.xjnt.schoolarium_api.persistence.PersonRepository
import xyz.xjnt.schoolarium_api.presentation.request.PersonRequest
import xyz.xjnt.schoolarium_api.presentation.request.UpdatePersonRequest
import kotlin.test.assertEquals

class PersonServiceTest() {
    private val personRepository = mockk<PersonRepository>()
    private val personService = PersonService(personRepository)

    @Test
    fun `get person by id`() {
        val person = Person(1L, "John", "Doe", null)
        every { personRepository.findByIdOrNull(1L) } returns person

        val result = personService.findById(1L)

        assertNotNull(result)
        assertEquals(person.name, result.name)
    }

    @Test
    fun `find all persons`() {
        val persons = listOf(
            Person(1L, "John", "Doe", "Smith"),
            Person(2L, "Jane", "Roe", null)
        )
        every { personRepository.findAll() } returns persons

        val result = personService.findAll()

        assertEquals(2, result.size)
        assertEquals("John", result[0].name)
        assertEquals("Doe", result[0].firstSurname)
        assertEquals("Smith", result[0].secondSurname)
        assertEquals("Jane", result[1].name)
        assertEquals("Roe", result[1].firstSurname)
        assertEquals(null, result[1].secondSurname)
    }

    @Test
    fun `save person`() {
        val request = PersonRequest("Alice", "Wonder", "Land")
        val savedPerson = Person(3L, "Alice", "Wonder", "Land")
        every { personRepository.save(any()) } returns savedPerson

        val result = personService.save(request)

        assertEquals(3L, result.id)
        assertEquals("Alice", result.name)
        assertEquals("Wonder", result.firstSurname)
        assertEquals("Land", result.secondSurname)
    }

    @Test
    fun `delete person by id`() {
        every { personRepository.deleteById(4L) } returns Unit

        personService.deleteById(4L)

        verify(exactly = 1) { personRepository.deleteById(4L) }
    }

    @Test
    fun `update person successfully`() {
        val id = 1L
        val original = Person(id, "John", "Doe", null)
        val updated = Person(id, "Jane", "Doe", "Smith")
        val updateRequest = UpdatePersonRequest(
            JsonNullable.of("Jane"),
            JsonNullable.undefined(),
            JsonNullable.of("Smith")
        )
        every { personRepository.findByIdOrNull(id) } returns original
        every { personRepository.save(any()) } returns updated

        val result = personService.update(id, updateRequest)

        assertEquals("Jane", result.name)
        assertEquals("Doe", result.firstSurname)
        assertEquals("Smith", result.secondSurname)
    }

    @Test
    fun `update person throws when not found`() {
        val id = 99L
        val updateRequest = UpdatePersonRequest(
            JsonNullable.of("Jane"),
            JsonNullable.undefined(),
            JsonNullable.of("Smith")
        )
        every { personRepository.findByIdOrNull(id) } returns null

        val exception = assertThrows<NoSuchElementException> {
            personService.update(id, updateRequest)
        }

        assertEquals("Person with id 99 not found", exception.message)
    }
}