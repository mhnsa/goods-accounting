package ru.mkhalikov.warehouse.goods_accounting.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


/**
 * Информация о складе
 **/
@Getter
@Setter
@Builder
@AllArgsConstructor
public class WarehouseDTO {

    private Integer id;
    private String name;
    private LocalDateTime createdDttm;
    private LocalDateTime updatedDttm;
}
