package xyz.xjnt.schoolarium_api.presentation.controller

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import xyz.xjnt.schoolarium_api.business.service.StudentService
import xyz.xjnt.schoolarium_api.exception.type.NotFoundException
import xyz.xjnt.schoolarium_api.presentation.request.StudentRequest
import xyz.xjnt.schoolarium_api.presentation.response.StudentResponse

@RestController
@RequestMapping("/api/students")
class StudentController(private val studentService: StudentService) {

    @GetMapping
    fun findAll(): List<StudentResponse> = studentService.findAll()

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): StudentResponse {
        val student = studentService.findById(id)
            ?: throw NotFoundException("Student with id $id not found")

        return student
    }

    @PostMapping
    fun save(@RequestBody @Valid req: StudentRequest): StudentResponse =
        studentService.save(req)

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Long): ResponseEntity<Any> {
        val student = studentService.findById(id)
            ?: throw NotFoundException("Student with id $id not found")

        studentService.deleteById(student.id)

        return ResponseEntity.noContent().build()
    }

}