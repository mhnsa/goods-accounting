package ru.mkhalikov.warehouse.goods_accounting.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mkhalikov.warehouse.goods_accounting.dto.request.ReceiptDocumentRequestDTO;
import ru.mkhalikov.warehouse.goods_accounting.dto.ReceiveGoodsDTO;
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
    public void processReceipt(ReceiptDocumentRequestDTO receiptDocumentRequestDTO) {

        var destinationWarehouse = warehouseService.getEntityById(receiptDocumentRequestDTO.getDestinationWarehouseId());
        var receiveGoodsDTOList = receiptDocumentRequestDTO.getReceiveGoodsDTOList();

        receiveGoodsDTOList.forEach(receiveGoodsDTO -> {
            var productEntity = productRepository.findByArticleAndWarehouse(receiveGoodsDTO.getArticle(),
                    destinationWarehouse);

            if (productEntity == null) {
                productEntity = new ProductEntity();
                productEntity.setName(receiveGoodsDTO.getName());
                productEntity.setQuantity(receiveGoodsDTO.getQuantity());
                productEntity.setArticle(receiveGoodsDTO.getArticle());
                productEntity.setWarehouse(destinationWarehouse);
            } else {
                var newQuantity = productEntity.getQuantity() + receiveGoodsDTO.getQuantity();
                productEntity.setQuantity(newQuantity);
            }
            productEntity.setLastPurchasePrice(receiveGoodsDTO.getPurchasePrice());
            productRepository.save(productEntity);
        });

        ReceiptDocument receiptDocument = receiptDocumentRepository.save(ReceiptDocument.builder()
                .receiptNumber(receiptDocumentRequestDTO.getReceiptNumber())
                .destinationWarehouse(destinationWarehouse)
                .build());

        receivedGoodsRepository.saveAll(receiveGoodsDTOList.stream()
                .map(receiveGoodsDTO -> getReceivedGoodsEntityFromDTO(receiveGoodsDTO, receiptDocument))
                .collect(Collectors.toList()));
    }


    private ReceivedGoodsEntity getReceivedGoodsEntityFromDTO(ReceiveGoodsDTO receiveGoodsDTO, ReceiptDocument receiptDocument) {
        return ReceivedGoodsEntity.builder()
                .name(receiveGoodsDTO.getName())
                .article(receiveGoodsDTO.getArticle())
                .receiptDocument(receiptDocument)
                .quantity(receiveGoodsDTO.getQuantity())
                .purchasePrice(receiveGoodsDTO.getPurchasePrice())
                .build();
    }
}
