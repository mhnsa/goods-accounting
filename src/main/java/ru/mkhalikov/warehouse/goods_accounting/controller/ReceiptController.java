package ru.mkhalikov.warehouse.goods_accounting.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mkhalikov.warehouse.goods_accounting.dto.request.ReceiptDocumentRequestDTO;
import ru.mkhalikov.warehouse.goods_accounting.service.ReceiptService;

import javax.validation.Valid;

@Validated
@Tag(name = "Receipt", description = "API для работы с поступлением товаров")
@RequestMapping("/api/receipt")
@RestController
@RequiredArgsConstructor
public class ReceiptController {

    private final ReceiptService receiptService;

    @Operation(summary = "Обработка поступления товаров", tags = "Receipt")
    @PostMapping
    public void processReceipt(@Valid @RequestBody ReceiptDocumentRequestDTO receiptDocumentRequestDTO) {
        receiptService.processReceipt(receiptDocumentRequestDTO);
    }
}
