package ru.mkhalikov.warehouse.goods_accounting.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * Информация о товаре на складе
 **/
@Getter
@Setter
@Builder
@AllArgsConstructor
public class ProductResponseDTO {

    @Schema(title = "Идентификатор товара")
    private Integer id;

    @Schema(title = "Артикул")
    private String article;

    @Schema(title = "Наименование")
    private String name;

    @Schema(title = "Цена последней закупки")
    private BigDecimal lastPurchasePrice;

    @Schema(title = "Цена последней продажи")
    private BigDecimal lastSalePrice;

    @Schema(title = "Идентификатор склада")
    private Integer warehouseId;

    @Schema(title = "Количество")
    private Integer quantity;

    @Schema(title = "Время создания записи")
    private LocalDateTime createdDttm;

    @Schema(title = "Время обновления записи")
    private LocalDateTime updatedDttm;
}
