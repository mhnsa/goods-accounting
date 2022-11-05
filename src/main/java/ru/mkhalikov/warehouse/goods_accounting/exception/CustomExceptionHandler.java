package ru.mkhalikov.warehouse.goods_accounting.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler()
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public NotFoundResponseError handle(NotFoundException exception) {
        log.error(exception.getMessage(), exception);
        return new NotFoundResponseError(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
