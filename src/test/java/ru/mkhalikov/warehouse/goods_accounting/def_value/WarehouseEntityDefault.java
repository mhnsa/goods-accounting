package ru.mkhalikov.warehouse.goods_accounting.def_value;

import ru.mkhalikov.warehouse.goods_accounting.model.WarehouseEntity;

public class WarehouseEntityDefault {

    public static WarehouseEntity get() {
        return WarehouseEntity.builder().id(1)
                .name("Warehouse")
                .build();
    }
}
