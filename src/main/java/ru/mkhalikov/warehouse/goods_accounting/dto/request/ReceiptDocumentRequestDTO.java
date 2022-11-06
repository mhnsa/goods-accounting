package ru.mkhalikov.warehouse.goods_accounting.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.mkhalikov.warehouse.goods_accounting.dto.ReceiveGoodsDTO;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Документ о поступлении товаров
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptDocumentRequestDTO {

    @NotNull
    @Schema(title = "Номер документа")
    private String receiptNumber;

    @NotNull
    @Schema(title = "Идентификатор склада назначения")
    private Integer destinationWarehouseId;

    @Schema(title = "Перечень посутпивших товаров")
    private List<ReceiveGoodsDTO> receiveGoodsDTOList;
}
