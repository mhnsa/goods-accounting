package ru.mkhalikov.warehouse.goods_accounting.exception;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ValidationErrorResponse {

    private List<Violation> violations = new ArrayList<>();
}

@Data
class Violation {

    private final String fieldName;
    private final String message;
}
