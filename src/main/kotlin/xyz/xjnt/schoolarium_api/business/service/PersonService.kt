package xyz.xjnt.schoolarium_api.business.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import xyz.xjnt.schoolarium_api.business.mapper.toPerson
import xyz.xjnt.schoolarium_api.business.mapper.toResponse
import xyz.xjnt.schoolarium_api.business.model.Person
import xyz.xjnt.schoolarium_api.persistence.PersonRepository
import xyz.xjnt.schoolarium_api.presentation.request.PersonRequest
import xyz.xjnt.schoolarium_api.presentation.response.PersonResponse

@Service
class PersonService(private val personRepository: PersonRepository) {

    fun findAll(): List<PersonResponse> = personRepository.findAll().map(Person::toResponse)

    fun findById(id: Long): PersonResponse? = personRepository.findByIdOrNull(id)?.toResponse()

    fun save(toSave: PersonRequest): PersonResponse = personRepository.save(toSave.toPerson()).toResponse()

    fun deleteById(id: Long) = personRepository.deleteById(id)
}