package xyz.xjnt.schoolarium_api.exception.type

import org.springframework.http.HttpStatus
import xyz.xjnt.schoolarium_api.exception.ApiException

class NotFoundException(
    message: String? = null,
) : ApiException(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), message)