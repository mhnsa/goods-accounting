package ru.mkhalikov.warehouse.goods_accounting.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Поступивший товар
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReceiveGoodsRequestDTO {

    @NotNull
    @Schema(title = "Артикул")
    private String article;

    @NotNull
    @Schema(title = "Наименование")
    private String name;

    @NotNull
    @Min(1)
    @Schema(title = "Количество")
    private Integer quantity;

    @NotNull
    @Schema(title = "Цена закупки")
    private BigDecimal purchasePrice;
}
