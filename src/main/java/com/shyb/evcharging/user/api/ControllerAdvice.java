package com.shyb.evcharging.user.api;

import com.shyb.evcharging.user.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public ErrorResponse handleDataIntegrityViolationException(RuntimeException ex) {
        return new ErrorResponse("DATA_INTEGRITY_VIOLATION_ERROR", "데이터 저장/수정을 실패했습니다.");
    }
}

