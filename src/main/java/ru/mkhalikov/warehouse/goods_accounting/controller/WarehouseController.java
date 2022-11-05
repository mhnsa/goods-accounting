package ru.mkhalikov.warehouse.goods_accounting.controller;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.mkhalikov.warehouse.goods_accounting.dto.WarehouseDTO;
import ru.mkhalikov.warehouse.goods_accounting.service.WarehouseService;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@Validated
@RequestMapping("/api/warehouse")
@RestController
@RequiredArgsConstructor
public class WarehouseController {

    private final WarehouseService warehouseService;

    @PostMapping
    public Integer add(@Valid @RequestBody WarehouseDTO warehouseDTO) {
        return warehouseService.addWarehouse(warehouseDTO);
    }

    @GetMapping("/{id}")
    public WarehouseDTO getById(@Parameter(name = "id") @Min(0) @PathVariable(name = "id") Integer id) {
        return warehouseService.getById(id);
    }
}
