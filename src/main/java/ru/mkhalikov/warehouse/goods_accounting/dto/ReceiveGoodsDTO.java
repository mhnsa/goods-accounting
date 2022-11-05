package ru.mkhalikov.warehouse.goods_accounting.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Поступивший товар
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReceiveGoodsDTO {

    private String article;
    private String name;
    private Integer quantity;
    private BigDecimal purchasePrice;
}
