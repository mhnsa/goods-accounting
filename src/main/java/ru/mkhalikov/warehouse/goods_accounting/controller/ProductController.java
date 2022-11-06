package ru.mkhalikov.warehouse.goods_accounting.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.mkhalikov.warehouse.goods_accounting.dto.request.ProductRequestDTO;
import ru.mkhalikov.warehouse.goods_accounting.dto.response.ProductResponseDTO;
import ru.mkhalikov.warehouse.goods_accounting.service.ProductService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
@Tag(name = "Product", description = "API для работы с таблицей товаров на складе")
@RequestMapping("/api/product")
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "Добавление записи о товаре в базу", tags = "Product")
    @PostMapping
    public Integer add(@Valid @RequestBody ProductRequestDTO productRequestDTO) {
        return productService.addProduct(productRequestDTO);
    }

    @Operation(summary = "Получение информации о товаре по идентификатору", tags = "Product")
    @GetMapping("/{id}")
    public ProductResponseDTO getById(@Parameter(name = "id") @Min(0) @PathVariable(name = "id") Integer id) {
        return productService.getById(id);
    }

    @Operation(summary = "Получение информации о всех товарах в БД", tags = "Product")
    @GetMapping()
    public List<ProductResponseDTO> getAll() {
        return productService.getAll();
    }

    @Operation(summary = "Удаление информации о товаре по идентификатору", tags = "Product")
    @DeleteMapping("/{id}")
    public void deleteById(@Parameter(name = "id") @Min(0) @PathVariable(name = "id") Integer id) {
        productService.deleteById(id);
    }

}
