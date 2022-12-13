package com.kotlinstping.restwhitspringbootandkotlin.exception.handler

import com.kotlinstping.restwhitspringbootandkotlin.exception.ExceptionResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.*

@ControllerAdvice// used whenever it is necessary to use treatments that would be spread across several controllers
//every time the controller throws an exception that has not been handled, it falls directly into the controllerAdvice handling
@RestController
class CustomizedResponseEentityExceptionHandler: ResponseEntityExceptionHandler() {
         @ExceptionHandler(Exception::class)
    fun handleAllException(ex: Exception, request: WebRequest): ResponseEntity<ExceptionResponse> {

        val exceptionResponse = ExceptionResponse(
            Date(),
            ex.message,
            request.getDescription(false)
        )
        return ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }
    @ExceptionHandler(com.kotlinstping.restwhitspringbootandkotlin.exception.ResourceNotFoundException::class)
    fun ResourceNotFoundException(ex: Exception, request: WebRequest): ResponseEntity<ExceptionResponse> {

        val exceptionResponse = ExceptionResponse(
            Date(),
            ex.message,
            request.getDescription(false)
        )
        return ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.NOT_FOUND)
    }

}