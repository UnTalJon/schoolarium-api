package xyz.xjnt.schoolarium_api.business.mapper

import xyz.xjnt.schoolarium_api.business.model.Person
import xyz.xjnt.schoolarium_api.business.model.Student
import xyz.xjnt.schoolarium_api.presentation.request.StudentRequest
import xyz.xjnt.schoolarium_api.presentation.response.StudentResponse

fun StudentRequest.toStudent(person: Person): Student = Student(0, grade, section, program, person)

fun Student.toResponse() = StudentResponse(id, grade, section, program, person.toResponse())