package ru.mkhalikov.warehouse.goods_accounting.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.mkhalikov.warehouse.goods_accounting.dto.request.WarehouseRequestDTO;
import ru.mkhalikov.warehouse.goods_accounting.dto.response.WarehouseResponseDTO;
import ru.mkhalikov.warehouse.goods_accounting.service.WarehouseService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Tag(name = "Warehouse", description = "API для работы с таблицей складов")
@RequestMapping("/api/warehouse")
@RestController
@RequiredArgsConstructor
public class WarehouseController {

    private final WarehouseService warehouseService;

    @Operation(summary = "Добавление склада в базу", tags = "Warehouse")
    @PostMapping
    public WarehouseResponseDTO add(@RequestBody @Valid WarehouseRequestDTO warehouseRequestDTO) {
        return warehouseService.addWarehouse(warehouseRequestDTO);
    }

    @Operation(summary = "Получение информации о складе по идентификатору", tags = "Warehouse")
    @GetMapping("/{id}")
    public WarehouseResponseDTO getById(@Parameter(name = "id") @Min(1) @PathVariable(name = "id") Integer id) {
        return warehouseService.getById(id);
    }

    @Operation(summary = "Получение информации о всех складах", tags = "Warehouse")
    @GetMapping()
    public List<WarehouseResponseDTO> getAll() {
        return warehouseService.getAll();
    }

    @Operation(summary = "Удаление информации о складе по идентификатору", tags = "Warehouse")
    @DeleteMapping("/{id}")
    public void deleteById(@Parameter(name = "id") @Min(1) @PathVariable(name = "id") Integer id) {
        warehouseService.deleteById(id);
    }
}
