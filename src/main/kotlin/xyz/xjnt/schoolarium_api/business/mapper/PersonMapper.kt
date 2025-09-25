package xyz.xjnt.schoolarium_api.business.mapper

import xyz.xjnt.schoolarium_api.business.model.Person
import xyz.xjnt.schoolarium_api.presentation.request.PersonRequest
import xyz.xjnt.schoolarium_api.presentation.response.PersonResponse

fun PersonRequest.toPerson(): Person = Person(0, name, firstSurname, secondSurname)

fun Person.toResponse(): PersonResponse = PersonResponse(id, name, firstSurname, secondSurname)