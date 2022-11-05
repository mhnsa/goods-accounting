package ru.mkhalikov.warehouse.goods_accounting.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mkhalikov.warehouse.goods_accounting.dto.WarehouseDTO;
import ru.mkhalikov.warehouse.goods_accounting.exception.NotFoundException;
import ru.mkhalikov.warehouse.goods_accounting.model.Warehouse;
import ru.mkhalikov.warehouse.goods_accounting.repository.WarehouseRepository;
import ru.mkhalikov.warehouse.goods_accounting.service.WarehouseService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseRepository warehouseRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer addWarehouse(WarehouseDTO warehouseDTO) {
        var warehouse = Warehouse.builder().name(warehouseDTO.getName()).build();

        return warehouseRepository.save(warehouse).getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WarehouseDTO getById(Integer id) {
        var warehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Warehouse with id %s not found", id)));
        return WarehouseDTO.builder()
                .name(warehouse.getName())
                .createdDttm(warehouse.getCreatedDttm())
                .updatedDttm(warehouse.getUpdatedDttm())
                .build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<WarehouseDTO> getAll() {
        return warehouseRepository.findAll().stream()
                .map(this::getWarehouseDTOFromWarehouseEntity)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(Integer id) {
        var warehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Warehouse with id %s not found", id)));
        if (warehouse != null) {
            warehouseRepository.deleteById(id);
        }
    }

    private WarehouseDTO getWarehouseDTOFromWarehouseEntity(Warehouse warehouse) {
        return WarehouseDTO.builder()
                .id(warehouse.getId())
                .name(warehouse.getName())
                .createdDttm(warehouse.getCreatedDttm())
                .updatedDttm(warehouse.getUpdatedDttm())
                .build();
    }
}
