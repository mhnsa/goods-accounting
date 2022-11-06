package ru.mkhalikov.warehouse.goods_accounting.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mkhalikov.warehouse.goods_accounting.dto.request.ReceiptDocumentRequestDTO;
import ru.mkhalikov.warehouse.goods_accounting.dto.request.ReceiveGoodsRequestDTO;
import ru.mkhalikov.warehouse.goods_accounting.model.ProductEntity;
import ru.mkhalikov.warehouse.goods_accounting.model.document.ReceiptDocument;
import ru.mkhalikov.warehouse.goods_accounting.model.document.goods.ReceivedGoodsEntity;
import ru.mkhalikov.warehouse.goods_accounting.repository.ProductRepository;
import ru.mkhalikov.warehouse.goods_accounting.repository.ReceiptDocumentRepository;
import ru.mkhalikov.warehouse.goods_accounting.repository.ReceivedGoodsRepository;
import ru.mkhalikov.warehouse.goods_accounting.service.ReceiptService;
import ru.mkhalikov.warehouse.goods_accounting.service.WarehouseService;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReceiptServiceImpl implements ReceiptService {
    private final ReceiptDocumentRepository receiptDocumentRepository;
    private final ReceivedGoodsRepository receivedGoodsRepository;
    private final WarehouseService warehouseService;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public void processReceipt(ReceiptDocumentRequestDTO receiptDocumentRequestDTO) {

        var destinationWarehouse = warehouseService.getEntityById(receiptDocumentRequestDTO.getDestinationWarehouseId());
        var receiveGoodsDTOList = receiptDocumentRequestDTO.getReceiveGoodsRequestDTOList();

        receiveGoodsDTOList.forEach(receiveGoodsRequestDTO -> {
            var productEntity = productRepository.findByArticleAndWarehouse(receiveGoodsRequestDTO.getArticle(),
                    destinationWarehouse).orElse(new ProductEntity());

            if (productEntity.getId() == null) {
                productEntity.setName(receiveGoodsRequestDTO.getName());
                productEntity.setQuantity(receiveGoodsRequestDTO.getQuantity());
                productEntity.setArticle(receiveGoodsRequestDTO.getArticle());
                productEntity.setWarehouse(destinationWarehouse);
            } else {
                var newQuantity = productEntity.getQuantity() + receiveGoodsRequestDTO.getQuantity();
                productEntity.setQuantity(newQuantity);
            }
            productEntity.setLastPurchasePrice(receiveGoodsRequestDTO.getPurchasePrice());
            productRepository.save(productEntity);
        });

        ReceiptDocument receiptDocument = receiptDocumentRepository.save(ReceiptDocument.builder()
                .receiptNumber(receiptDocumentRequestDTO.getReceiptNumber())
                .destinationWarehouse(destinationWarehouse)
                .build());

        receivedGoodsRepository.saveAll(receiveGoodsDTOList.stream()
                .map(receiveGoodsRequestDTO -> getReceivedGoodsEntityFromDTO(receiveGoodsRequestDTO, receiptDocument))
                .collect(Collectors.toList()));
    }


    private ReceivedGoodsEntity getReceivedGoodsEntityFromDTO(ReceiveGoodsRequestDTO receiveGoodsRequestDTO, ReceiptDocument receiptDocument) {
        return ReceivedGoodsEntity.builder()
                .name(receiveGoodsRequestDTO.getName())
                .article(receiveGoodsRequestDTO.getArticle())
                .receiptDocument(receiptDocument)
                .quantity(receiveGoodsRequestDTO.getQuantity())
                .purchasePrice(receiveGoodsRequestDTO.getPurchasePrice())
                .build();
    }
}
