package ru.mkhalikov.warehouse.goods_accounting.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mkhalikov.warehouse.goods_accounting.dto.request.ProductRequestDTO;
import ru.mkhalikov.warehouse.goods_accounting.dto.response.ProductResponseDTO;
import ru.mkhalikov.warehouse.goods_accounting.exception.NotFoundException;
import ru.mkhalikov.warehouse.goods_accounting.model.ProductEntity;
import ru.mkhalikov.warehouse.goods_accounting.repository.ProductRepository;
import ru.mkhalikov.warehouse.goods_accounting.repository.WarehouseRepository;
import ru.mkhalikov.warehouse.goods_accounting.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final WarehouseRepository warehouseRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer addProduct(ProductRequestDTO productRequestDTO) {
        var warehouse = warehouseRepository.findById(productRequestDTO.getWarehouseId())
                .orElseThrow(() -> new NotFoundException(String.format("Warehouse with id %s not found", productRequestDTO.getWarehouseId())));

        var productEntity = ProductEntity.builder()
                .article(productRequestDTO.getArticle())
                .quantity(productRequestDTO.getQuantity())
                .warehouse(warehouse)
                .lastPurchasePrice(productRequestDTO.getLastPurchasePrice())
                .lastSalePrice(productRequestDTO.getLastSalePrice())
                .name(productRequestDTO.getName())
                .build();

        return productRepository.save(productEntity).getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProductResponseDTO getById(Integer id) {
        var productEntity = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Product with id %s not found", id)));
        return getProductDTOFromProductEntity(productEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProductResponseDTO> getAll() {
        return productRepository.findAll().stream()
                .map(this::getProductDTOFromProductEntity)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(Integer id) {
        var warehouse = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Product with id %s not found", id)));
        if (warehouse != null) {
            productRepository.deleteById(id);
        }
    }

    private ProductResponseDTO getProductDTOFromProductEntity(ProductEntity productEntity) {
        return ProductResponseDTO.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .article(productEntity.getArticle())
                .warehouseId(productEntity.getWarehouse().getId())
                .quantity(productEntity.getQuantity())
                .lastSalePrice(productEntity.getLastSalePrice())
                .lastPurchasePrice(productEntity.getLastPurchasePrice())
                .createdDttm(productEntity.getCreatedDttm())
                .updatedDttm(productEntity.getUpdatedDttm())
                .build();
    }
}
