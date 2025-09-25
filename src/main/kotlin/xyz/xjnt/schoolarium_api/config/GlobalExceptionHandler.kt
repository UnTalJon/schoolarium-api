package xyz.xjnt.schoolarium_api.config

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.ServletWebRequest
import org.springframework.web.context.request.WebRequest
import xyz.xjnt.schoolarium_api.exception.ApiException
import xyz.xjnt.schoolarium_api.presentation.response.ExceptionResponse


@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(ApiException::class)
    fun handleApiException(
        ex: ApiException,
        req: WebRequest
    ): ResponseEntity<ExceptionResponse> {
        val body = ExceptionResponse(
            timestamp = java.time.Instant.now(),
            status = ex.error,
            error = ex.status.reasonPhrase,
            message = ex.message,
            path = (req as ServletWebRequest).request.requestURI
        )
        return ResponseEntity(body, ex.status)
    }
}