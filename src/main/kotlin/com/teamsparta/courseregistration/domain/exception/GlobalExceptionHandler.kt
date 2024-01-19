package com.teamsparta.courseregistration.domain.exception

import com.teamsparta.courseregistration.domain.exception.dto.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
class GlobalExceptionHandler { //모든 예외상황 코드 작성, 관리하도록 한다.

    @ExceptionHandler(ModelNotFoundException::class)
    fun headleModelNotFoundException(e: ModelNotFoundException): ResponseEntity<ErrorResponse> {//리스폰스엔티티<dto입력>
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ErrorResponse( e.message, ""))
    }

    @ExceptionHandler(IllegalStateException::class)
    fun handleIlegalSataeException(e: IllegalStateException): ResponseEntity<ErrorResponse>{
        return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .body(ErrorResponse(e.message, ""))
    }
}