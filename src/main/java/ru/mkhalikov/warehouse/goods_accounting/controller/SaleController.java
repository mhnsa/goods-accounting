package ru.mkhalikov.warehouse.goods_accounting.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mkhalikov.warehouse.goods_accounting.dto.request.SaleDocumentRequestDTO;
import ru.mkhalikov.warehouse.goods_accounting.service.SaleService;

import javax.validation.Valid;

@Validated
@Tag(name = "Sale", description = "API для работы с продажей товаров")
@RequestMapping("/api/sale")
@RestController
@RequiredArgsConstructor
public class SaleController {

    private final SaleService saleService;

    @Operation(summary = "Обработка продажи товаров", tags = "Sale")
    @PostMapping
    public void processSale(@Valid @RequestBody SaleDocumentRequestDTO saleDocumentRequestDTO) {
        saleService.processSale(saleDocumentRequestDTO);
    }
}
