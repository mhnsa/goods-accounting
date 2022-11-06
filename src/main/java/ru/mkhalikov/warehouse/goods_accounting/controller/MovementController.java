package ru.mkhalikov.warehouse.goods_accounting.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mkhalikov.warehouse.goods_accounting.dto.request.MovementDocumentRequestDTO;
import ru.mkhalikov.warehouse.goods_accounting.service.MovementService;

import javax.validation.Valid;

@Validated
@Tag(name = "Movement", description = "API для работы с перемещением товаров")
@RequestMapping("/api/movement")
@RestController
@RequiredArgsConstructor
public class MovementController {

    private final MovementService movementService;

    @Operation(summary = "Обработка перемещения товаров", tags = "Movement")
    @PostMapping
    public void processMovement(@Valid @RequestBody MovementDocumentRequestDTO movementDocumentRequestDTO) {
        movementService.processMovement(movementDocumentRequestDTO);
    }
}
