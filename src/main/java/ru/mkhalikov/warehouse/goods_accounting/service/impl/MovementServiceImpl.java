package ru.mkhalikov.warehouse.goods_accounting.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mkhalikov.warehouse.goods_accounting.dto.request.MovedGoodsRequestDTO;
import ru.mkhalikov.warehouse.goods_accounting.dto.request.MovementDocumentRequestDTO;
import ru.mkhalikov.warehouse.goods_accounting.exception.IncorrectDataException;
import ru.mkhalikov.warehouse.goods_accounting.exception.NotFoundException;
import ru.mkhalikov.warehouse.goods_accounting.model.ProductEntity;
import ru.mkhalikov.warehouse.goods_accounting.model.document.MovementDocumentEntity;
import ru.mkhalikov.warehouse.goods_accounting.model.document.goods.MovedGoodsEntity;
import ru.mkhalikov.warehouse.goods_accounting.repository.MovedGoodsRepository;
import ru.mkhalikov.warehouse.goods_accounting.repository.MovementDocumentRepository;
import ru.mkhalikov.warehouse.goods_accounting.repository.ProductRepository;
import ru.mkhalikov.warehouse.goods_accounting.service.MovementService;
import ru.mkhalikov.warehouse.goods_accounting.service.WarehouseService;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovementServiceImpl implements MovementService {
    private final MovementDocumentRepository movementDocumentRepository;
    private final MovedGoodsRepository movedGoodsRepository;
    private final WarehouseService warehouseService;
    private final ProductRepository productRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void processMovement(MovementDocumentRequestDTO movementDocumentRequestDTO) {

        var sourceWarehouse = warehouseService.getEntityById(movementDocumentRequestDTO.getSourceWarehouseId());
        var destinationWarehouse = warehouseService.getEntityById(movementDocumentRequestDTO.getDestinationWarehouseId());
        var movedGoodsRequestDTOList = movementDocumentRequestDTO.getMovedGoodsRequestDTOList();

        movedGoodsRequestDTOList.forEach(movedGoodsRequestDTO -> {
            var sourceProductEntity = productRepository.findByArticleAndWarehouse(movedGoodsRequestDTO.getArticle(),
                    sourceWarehouse).orElseThrow(() -> new NotFoundException(String.format("Product with article %s in " +
                    "warehouse %s not found", movedGoodsRequestDTO.getArticle(), sourceWarehouse.getName())));

            var newSourceQuantity = sourceProductEntity.getQuantity() - movedGoodsRequestDTO.getQuantity();

            if (newSourceQuantity < 0) {
                throw new IncorrectDataException("Incorrect data in MovedDocument");
            }
            sourceProductEntity.setQuantity(newSourceQuantity);

            var destinationProductEntity = productRepository.findByArticleAndWarehouse(movedGoodsRequestDTO.getArticle(),
                    destinationWarehouse).orElse(new ProductEntity());

            if (destinationProductEntity.getId() == null) {
                destinationProductEntity.setName(movedGoodsRequestDTO.getName());
                destinationProductEntity.setQuantity(movedGoodsRequestDTO.getQuantity());
                destinationProductEntity.setArticle(movedGoodsRequestDTO.getArticle());
                destinationProductEntity.setWarehouse(destinationWarehouse);
            } else {
                var newDestinationQuantity = destinationProductEntity.getQuantity() + movedGoodsRequestDTO.getQuantity();
                destinationProductEntity.setQuantity(newDestinationQuantity);
            }

            productRepository.save(sourceProductEntity);
            productRepository.save(destinationProductEntity);

        });

        MovementDocumentEntity movementDocument = movementDocumentRepository.save(MovementDocumentEntity.builder()
                .movementNumber(movementDocumentRequestDTO.getMovementNumber())
                .sourceWarehouse(sourceWarehouse)
                .destinationWarehouse(destinationWarehouse)
                .build());

        movedGoodsRepository.saveAll(movedGoodsRequestDTOList.stream().
                map(soldGoodsRequestDTO -> getMovedGoodsEntityFromDTO(soldGoodsRequestDTO, movementDocument)).
                collect(Collectors.toList()));
    }

    private MovedGoodsEntity getMovedGoodsEntityFromDTO(MovedGoodsRequestDTO movedGoodsRequestDTO,
                                                        MovementDocumentEntity movementDocument) {
        return MovedGoodsEntity.builder()
                .name(movedGoodsRequestDTO.getName())
                .article(movedGoodsRequestDTO.getArticle())
                .movementDocument(movementDocument)
                .quantity(movedGoodsRequestDTO.getQuantity())
                .build();
    }
}
