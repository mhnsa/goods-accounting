package ru.mkhalikov.warehouse.goods_accounting.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Документ о поступлении товаров
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptDocumentDTO {

    private String receiptNumber;
    private Integer destinationWarehouseId;
    private List<ReceiveGoodsDTO> receiveGoodsDTOList;
}
