package ru.mkhalikov.warehouse.goods_accounting.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


/**
 * Модель информации о товаре (запрос)
 **/
@Getter
@Setter
@Builder
@AllArgsConstructor
public class ProductRequestDTO {

    @NotNull
    @Schema(title = "Артикул")
    private String article;

    @NotNull
    @Schema(title = "Наименование товара")
    private String name;

    @NotNull
    @Schema(title = "Последняя цена закупки")
    private BigDecimal lastPurchasePrice;

    @NotNull
    @Schema(title = "Последняя цена продажи")
    private BigDecimal lastSalePrice;

    @NotNull
    @Schema(title = "Идентификатор склада")
    private Integer warehouseId;

    @NotNull
    @Min(1)
    @Schema(title = "Количество")
    private Integer quantity;
}
