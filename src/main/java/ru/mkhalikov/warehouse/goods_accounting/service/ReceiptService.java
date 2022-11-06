package ru.mkhalikov.warehouse.goods_accounting.service;

import ru.mkhalikov.warehouse.goods_accounting.dto.request.ReceiptDocumentRequestDTO;

/**
 * Сервис для работы с поступлением товаров
 **/
public interface ReceiptService {

    /**
     * Метод обработки документов о поступлении товаров
     *
     * @param receiptDocumentRequestDTO Документ о поступлении товаров
     */
    void processReceipt(ReceiptDocumentRequestDTO receiptDocumentRequestDTO);
}
