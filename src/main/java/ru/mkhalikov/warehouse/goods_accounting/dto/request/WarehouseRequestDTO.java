package ru.mkhalikov.warehouse.goods_accounting.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotNull;


/**
 * Модель информации о складе (запрос)
 **/
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseRequestDTO {

    @Schema(title = "Наименование склада", required = true)
    @NotNull
    private String name;

}
