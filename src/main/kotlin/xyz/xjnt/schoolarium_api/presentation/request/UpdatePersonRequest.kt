package xyz.xjnt.schoolarium_api.presentation.request

import com.fasterxml.jackson.annotation.JsonProperty
import org.openapitools.jackson.nullable.JsonNullable

data class UpdatePersonRequest(
    val name: JsonNullable<String> = JsonNullable.undefined(),

    @field:JsonProperty("first_surname")
    val firstSurname: JsonNullable<String> = JsonNullable.undefined(),

    @field:JsonProperty("second_surname")
    val secondSurname: JsonNullable<String> = JsonNullable.undefined(),
)