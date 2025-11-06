package xyz.xjnt.schoolarium_api.business.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import xyz.xjnt.schoolarium_api.business.mapper.toPerson
import xyz.xjnt.schoolarium_api.business.mapper.toResponse
import xyz.xjnt.schoolarium_api.business.model.Person
import xyz.xjnt.schoolarium_api.persistence.PersonRepository
import xyz.xjnt.schoolarium_api.presentation.request.PersonRequest
import xyz.xjnt.schoolarium_api.presentation.request.UpdatePersonRequest
import xyz.xjnt.schoolarium_api.presentation.response.PersonResponse

@Service
class PersonService(private val personRepository: PersonRepository) {

    fun findAll(): List<PersonResponse> = personRepository.findAll().map(Person::toResponse)

    fun findById(id: Long): PersonResponse? = personRepository.findByIdOrNull(id)?.toResponse()

    fun save(toSave: PersonRequest): PersonResponse = personRepository.save(toSave.toPerson()).toResponse()

    @Transactional
    fun update(id: Long, toUpdate: UpdatePersonRequest): PersonResponse {
        val person = personRepository.findByIdOrNull(id)
            ?: throw NoSuchElementException("Person with id $id not found")

        toUpdate.name.ifPresent { person.name = it }
        toUpdate.firstSurname.ifPresent { person.firstSurname = it }
        toUpdate.secondSurname.ifPresent { person.secondSurname = it }

        val update = personRepository.save(person)

        return update.toResponse()
    }

    fun deleteById(id: Long) = personRepository.deleteById(id)
}