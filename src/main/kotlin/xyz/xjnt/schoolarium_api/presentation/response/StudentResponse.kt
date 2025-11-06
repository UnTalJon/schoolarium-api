package xyz.xjnt.schoolarium_api.presentation.response

data class StudentResponse(
    val id: String,
    val grade: String,
    val section: String,
    val program: String,
    val person: PersonResponse,
)
