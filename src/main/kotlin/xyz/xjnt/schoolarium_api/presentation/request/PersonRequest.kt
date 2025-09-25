package xyz.xjnt.schoolarium_api.presentation.request

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank

data class PersonRequest(
    @field:NotBlank
    val name: String,

    @field:JsonProperty("first_surname")
    @field:NotBlank
    val firstSurname: String,

    @field:JsonProperty("second_surname")
    val secondSurname: String? = null,
)
