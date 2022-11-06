package ru.mkhalikov.warehouse.goods_accounting.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mkhalikov.warehouse.goods_accounting.dto.request.SaleDocumentRequestDTO;
import ru.mkhalikov.warehouse.goods_accounting.dto.request.SoldGoodsRequestDTO;
import ru.mkhalikov.warehouse.goods_accounting.exception.IncorrectDataException;
import ru.mkhalikov.warehouse.goods_accounting.exception.NotFoundException;
import ru.mkhalikov.warehouse.goods_accounting.model.document.SaleDocument;
import ru.mkhalikov.warehouse.goods_accounting.model.document.goods.SoldGoodsEntity;
import ru.mkhalikov.warehouse.goods_accounting.repository.ProductRepository;
import ru.mkhalikov.warehouse.goods_accounting.repository.SaleDocumentRepository;
import ru.mkhalikov.warehouse.goods_accounting.repository.SoldGoodsRepository;
import ru.mkhalikov.warehouse.goods_accounting.service.SaleService;
import ru.mkhalikov.warehouse.goods_accounting.service.WarehouseService;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {
    private final SaleDocumentRepository saleDocumentRepository;
    private final SoldGoodsRepository soldGoodsRepository;
    private final WarehouseService warehouseService;
    private final ProductRepository productRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void processSale(SaleDocumentRequestDTO saleDocumentRequestDTO) {

        var sourceWarehouse = warehouseService.getEntityById(saleDocumentRequestDTO.getSourceWarehouseId());
        var soldGoodsDTOList = saleDocumentRequestDTO.getSoldGoodsRequestDTOList();

        soldGoodsDTOList.forEach(soldGoodsRequestDTO -> {
            var productEntity = productRepository.findByArticleAndWarehouse(soldGoodsRequestDTO.getArticle(),
                    sourceWarehouse).orElseThrow(() -> new NotFoundException(String.format("Product with article %s in " +
                    "warehouse %s not found", soldGoodsRequestDTO.getArticle(), sourceWarehouse.getName())));

            var newQuantity = productEntity.getQuantity() - soldGoodsRequestDTO.getQuantity();

            if (newQuantity < 0) {
                throw new IncorrectDataException("Incorrect data in SaleDocument");
            }

            productEntity.setQuantity(newQuantity);
            productRepository.save(productEntity);
        });

        SaleDocument saleDocument = saleDocumentRepository.save(SaleDocument.builder()
                .saleNumber(saleDocumentRequestDTO.getSaleNumber())
                .sourceWarehouse(sourceWarehouse)
                .build());

        soldGoodsRepository.saveAll(soldGoodsDTOList.stream().
                map(soldGoodsRequestDTO -> getSoldGoodsEntityFromDTO(soldGoodsRequestDTO, saleDocument)).
                collect(Collectors.toList()));
    }

    private SoldGoodsEntity getSoldGoodsEntityFromDTO(SoldGoodsRequestDTO soldGoodsRequestDTO, SaleDocument saleDocument) {
        return SoldGoodsEntity.builder()
                .name(soldGoodsRequestDTO.getName())
                .article(soldGoodsRequestDTO.getArticle())
                .saleDocument(saleDocument)
                .quantity(soldGoodsRequestDTO.getQuantity())
                .salePrice(soldGoodsRequestDTO.getSalePrice())
                .build();
    }
}
