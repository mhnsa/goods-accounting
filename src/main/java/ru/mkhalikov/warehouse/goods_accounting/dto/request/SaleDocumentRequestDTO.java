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
 * Документ о продаже товаров
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleDocumentRequestDTO {

    @NotNull
    @Schema(title = "Номер документа")
    private String saleNumber;

    @NotNull
    @Schema(title = "Идентификатор склада источника")
    private Integer sourceWarehouseId;

    @Schema(title = "Перечень проданных товаров")
    @Valid
    private List<SoldGoodsRequestDTO> soldGoodsRequestDTOList;
}
