package ru.mkhalikov.warehouse.goods_accounting.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Документ о перемещении товаров
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovementDocumentRequestDTO {

    @NotNull
    @Schema(title = "Номер документа")
    private String movementNumber;

    @NotNull
    @Schema(title = "Идентификатор склада источника")
    private Integer sourceWarehouseId;

    @NotNull
    @Schema(title = "Идентификатор склада назначения")
    private Integer destinationWarehouseId;

    @Schema(title = "Перечень перемещенных товаров")
    @Valid
    private List<MovedGoodsRequestDTO> movedGoodsRequestDTOList;
}
