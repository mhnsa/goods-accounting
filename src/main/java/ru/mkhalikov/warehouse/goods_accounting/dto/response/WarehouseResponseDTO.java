package ru.mkhalikov.warehouse.goods_accounting.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
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
public class WarehouseResponseDTO {

    @Schema(name = "Идентификатор")
    private Integer id;

    @Schema(name = "Наименование склада")
    private String name;

    @Schema(name = "Время создания записи")
    private LocalDateTime createdDttm;

    @Schema(name = "Время обновления записи")
    private LocalDateTime updatedDttm;
}
