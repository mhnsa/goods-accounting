package ru.mkhalikov.warehouse.goods_accounting.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mkhalikov.warehouse.goods_accounting.dto.ReceiptDocumentDTO;
import ru.mkhalikov.warehouse.goods_accounting.repository.ReceiptDocumentRepository;
import ru.mkhalikov.warehouse.goods_accounting.service.DocumentService;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {
    private final ReceiptDocumentRepository receiptDocumentRepository;

    @Override
    public void receiveGoods(ReceiptDocumentDTO receiptDocumentDTO) {

    }
}
