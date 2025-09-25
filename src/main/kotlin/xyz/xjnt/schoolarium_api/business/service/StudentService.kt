package xyz.xjnt.schoolarium_api.business.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import xyz.xjnt.schoolarium_api.business.mapper.toPerson
import xyz.xjnt.schoolarium_api.business.mapper.toResponse
import xyz.xjnt.schoolarium_api.business.mapper.toStudent
import xyz.xjnt.schoolarium_api.persistence.PersonRepository
import xyz.xjnt.schoolarium_api.persistence.StudentRepository
import xyz.xjnt.schoolarium_api.presentation.request.StudentRequest
import xyz.xjnt.schoolarium_api.presentation.response.StudentResponse

@Service
class StudentService(
    private val studentRepository: StudentRepository,
    private val personRepository: PersonRepository
) {
    fun findAll(): List<StudentResponse> = studentRepository.findAll().map { it.toResponse() }

    fun findById(id: Long): StudentResponse? = studentRepository.findByIdOrNull(id)?.toResponse()

    @Transactional
    fun save(toSave: StudentRequest): StudentResponse {
        val person = personRepository.save(toSave.person.toPerson())
        val student = studentRepository.save(toSave.toStudent(person))

        return student.toResponse()
    }

    fun deleteById(id: Long) = studentRepository.deleteById(id)
}