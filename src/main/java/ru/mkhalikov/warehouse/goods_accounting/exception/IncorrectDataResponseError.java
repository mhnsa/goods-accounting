package ru.mkhalikov.warehouse.goods_accounting.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class IncorrectDataResponseError {
    private final HttpStatus status;
    private final String message;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime time = LocalDateTime.now();

    public IncorrectDataResponseError(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
}