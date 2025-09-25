package xyz.xjnt.schoolarium_api.presentation.response

import java.time.Instant

data class ExceptionResponse(
    val timestamp: Instant,
    val status: Int,
    val error: String,
    val message: String?,
    val path: String
)
