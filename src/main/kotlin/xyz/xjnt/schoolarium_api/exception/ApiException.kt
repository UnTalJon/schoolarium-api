package xyz.xjnt.schoolarium_api.exception

import org.springframework.http.HttpStatus

open class ApiException(
    val status: HttpStatus,
    val error: Int,
    message: String? = null,
    cause: Throwable? = null
) : RuntimeException(message, cause)