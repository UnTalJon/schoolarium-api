package xyz.xjnt.schoolarium_api.presentation.controller

import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import xyz.xjnt.schoolarium_api.business.service.PersonService
import xyz.xjnt.schoolarium_api.exception.type.NotFoundException
import xyz.xjnt.schoolarium_api.presentation.request.PersonRequest
import xyz.xjnt.schoolarium_api.presentation.request.UpdatePersonRequest
import xyz.xjnt.schoolarium_api.presentation.response.PersonResponse

@RestController
@RequestMapping("/api/persons")
class PersonController(private val personService: PersonService) {
    @GetMapping
    fun findAll(): List<PersonResponse> = personService.findAll()

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): PersonResponse =
        personService.findById(id)
            ?: throw NotFoundException("Person with id $id not found")

    @PostMapping
    fun save(@RequestBody @Valid req: PersonRequest): PersonResponse =
        personService.save(req)

    @PatchMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody req: UpdatePersonRequest): PersonResponse =
        personService.update(id, req)

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Long): ResponseEntity<Any> {
        val person = personService.findById(id)
            ?: throw NotFoundException("Person with id $id not found")

        personService.deleteById(person.id)

        return ResponseEntity.noContent().build()
    }
}