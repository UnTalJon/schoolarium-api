package xyz.xjnt.schoolarium_api.presentation.request

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank

data class PersonRequest(
    @param:NotBlank
    val name: String,

    @param:JsonProperty("first_surname")
    @param:NotBlank
    val firstSurname: String,

    @param:JsonProperty("second_surname")
    val secondSurname: String? = null,
)

