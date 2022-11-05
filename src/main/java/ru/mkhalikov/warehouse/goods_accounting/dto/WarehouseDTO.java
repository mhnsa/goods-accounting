package ru.mkhalikov.warehouse.goods_accounting.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class WarehouseDTO {

    private Long id;
    private String name;
}
