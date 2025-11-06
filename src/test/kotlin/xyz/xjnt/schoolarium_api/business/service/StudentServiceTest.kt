package xyz.xjnt.schoolarium_api.business.service

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertNotNull
import org.springframework.data.repository.findByIdOrNull
import xyz.xjnt.schoolarium_api.business.mapper.toResponse
import xyz.xjnt.schoolarium_api.business.model.Person
import xyz.xjnt.schoolarium_api.business.model.Student
import xyz.xjnt.schoolarium_api.persistence.PersonRepository
import xyz.xjnt.schoolarium_api.persistence.StudentRepository
import xyz.xjnt.schoolarium_api.presentation.request.PersonRequest
import xyz.xjnt.schoolarium_api.presentation.request.StudentRequest
import kotlin.test.assertEquals

class StudentServiceTest {
    private val studentRepository = mockk<StudentRepository>()
    private val personRepository = mockk<PersonRepository>()
    private val studentService = StudentService(studentRepository, personRepository)

    @Test
    fun `get student by id`() {
        val person = Person(1L, "John", "Doe", null)
        val student = Student("STU123", "7", "B-12", "Math", person)
        every { studentRepository.findByIdOrNull("STU123") } returns student

        val result = studentService.findById("STU123")

        assertNotNull(result)
        assertEquals(student.toResponse(), result)
    }

    @Test
    fun `findAll returns list of students`() {
        val person = Person(1L, "John", "Doe", null)
        val student = Student("STU123", "7", "B-12", "Math", person)
        every { studentRepository.findAll() } returns listOf(student)

        val result = studentService.findAll()

        assertEquals(listOf(student.toResponse()), result)
    }

    @Test
    fun `findAll returns empty list when no students`() {
        every { studentRepository.findAll() } returns emptyList()

        val result = studentService.findAll()

        assertEquals(emptyList(), result)
    }

    @Test
    fun `findById returns null when student does not exist`() {
        every { studentRepository.findByIdOrNull("STU999") } returns null

        val result = studentService.findById("STU999")

        assertEquals(null, result)
    }

    @Test
    fun `save student successfully`() {
        val personRequest = PersonRequest("John", "Doe", null)
        val studentRequest = StudentRequest("7", "B-12", "Math", personRequest)
        val person = Person(1L, "John", "Doe", null)
        val student = Student("STU123", "7", "B-12", "Math", person)
        every { personRepository.save(any()) } returns person
        every { studentRepository.save(any()) } returns student

        val result = studentService.save(studentRequest)

        assertEquals(student.toResponse(), result)
    }

    @Test
    fun `deleteById calls repository`() {
        every { studentRepository.deleteById("STU123") } returns Unit

        studentService.deleteById("STU123")

        verify { studentRepository.deleteById("STU123") }
    }
}