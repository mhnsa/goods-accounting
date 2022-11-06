package ru.mkhalikov.warehouse.goods_accounting.def_value;

import ru.mkhalikov.warehouse.goods_accounting.model.ProductEntity;

import java.math.BigDecimal;

public class ProductEntityDefault {

    public static ProductEntity get() {
        return ProductEntity.builder()
                .id(1)
                .article("123")
                .name("product")
                .lastPurchasePrice(BigDecimal.TEN)
                .lastSalePrice(BigDecimal.TEN)
                .warehouse(WarehouseEntityDefault.get())
                .quantity(1)
                .build();
    }
}
