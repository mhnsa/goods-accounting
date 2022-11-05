package ru.mkhalikov.warehouse.goods_accounting.service;

import ru.mkhalikov.warehouse.goods_accounting.dto.ReceiptDocumentDTO;

public interface DocumentService {
    void receiveGoods(ReceiptDocumentDTO receiptDocumentDTO);
}
