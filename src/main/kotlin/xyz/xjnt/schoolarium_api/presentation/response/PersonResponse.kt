package xyz.xjnt.schoolarium_api.presentation.response

import com.fasterxml.jackson.annotation.JsonProperty

data class PersonResponse(
    val id: Long,

    val name: String,

    @field:JsonProperty("first_surname")
    val firstSurname: String,

    @field:JsonProperty("second_surname")
    val secondSurname: String?,
)
