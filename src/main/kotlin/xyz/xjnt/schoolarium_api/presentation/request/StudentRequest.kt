package xyz.xjnt.schoolarium_api.presentation.request

import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank


data class StudentRequest(
    @field:NotBlank
    val grade: String,

    @field:NotBlank
    val section: String,

    @field:NotBlank
    val program: String,

    @field:Valid
    val person: PersonRequest,
)
