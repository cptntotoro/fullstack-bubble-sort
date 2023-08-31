package com.example.exceptioin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
@Slf4j
public class ArrayExceptionHandler {

    @ExceptionHandler(ArrayNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public Map<String, String> arrayNotFoundException(ArrayNotFoundException e) {
        log.error(e.getMessage());
        return Map.of("error", e.getMessage());
    }

    @ExceptionHandler(SQLConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Map<String, String> SQLConstraintViolationException(SQLConstraintViolationException e) {
        log.error(e.getMessage());
        return Map.of("error", e.getMessage());
    }

    @ExceptionHandler(ArrayIsEmptyException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Map<String, String> ArrayIsEmptyException(ArrayIsEmptyException e) {
        log.error(e.getMessage());
        return Map.of("error", e.getMessage());
    }

}
