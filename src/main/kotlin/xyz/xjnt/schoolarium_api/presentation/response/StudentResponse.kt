package xyz.xjnt.schoolarium_api.presentation.response

data class StudentResponse(
    val id: Long,
    val grade: String,
    val section: String,
    val program: String,
    val person: PersonResponse,
)
