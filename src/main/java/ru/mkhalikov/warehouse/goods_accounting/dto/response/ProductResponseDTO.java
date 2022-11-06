package ru.mkhalikov.warehouse.goods_accounting.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * Информация о товаре на складе
 **/
@Getter
@Setter
@Builder
@AllArgsConstructor
public class ProductResponseDTO {

    private Integer id;
    private String article;
    private String name;
    private BigDecimal lastPurchasePrice;
    private BigDecimal lastSalePrice;
    private Integer warehouseId;
    private Integer quantity;
    private LocalDateTime createdDttm;
    private LocalDateTime updatedDttm;
}
