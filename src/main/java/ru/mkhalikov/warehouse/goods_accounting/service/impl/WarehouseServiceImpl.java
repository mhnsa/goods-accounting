package ru.mkhalikov.warehouse.goods_accounting.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mkhalikov.warehouse.goods_accounting.dto.request.WarehouseRequestDTO;
import ru.mkhalikov.warehouse.goods_accounting.dto.response.WarehouseResponseDTO;
import ru.mkhalikov.warehouse.goods_accounting.exception.NotFoundException;
import ru.mkhalikov.warehouse.goods_accounting.model.WarehouseEntity;
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
    public Integer addWarehouse(WarehouseRequestDTO warehouseRequestDTO) {
        var warehouse = WarehouseEntity.builder().name(warehouseRequestDTO.getName()).build();

        return warehouseRepository.save(warehouse).getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WarehouseResponseDTO getById(Integer id) {
        var warehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Warehouse with id %s not found", id)));
        return WarehouseResponseDTO.builder()
                .name(warehouse.getName())
                .createdDttm(warehouse.getCreatedDttm())
                .updatedDttm(warehouse.getUpdatedDttm())
                .build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<WarehouseResponseDTO> getAll() {
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

    private WarehouseResponseDTO getWarehouseDTOFromWarehouseEntity(WarehouseEntity warehouseEntity) {
        return WarehouseResponseDTO.builder()
                .id(warehouseEntity.getId())
                .name(warehouseEntity.getName())
                .createdDttm(warehouseEntity.getCreatedDttm())
                .updatedDttm(warehouseEntity.getUpdatedDttm())
                .build();
    }
}
