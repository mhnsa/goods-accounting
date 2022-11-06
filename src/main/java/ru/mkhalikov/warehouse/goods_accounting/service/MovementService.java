package ru.mkhalikov.warehouse.goods_accounting.service;

import ru.mkhalikov.warehouse.goods_accounting.dto.request.MovementDocumentRequestDTO;

/**
 * Сервис для работы с перемещением товаров
 **/
public interface MovementService {

    /**
     * Метод обработки документов о перемещении товаров
     *
     * @param movementDocumentRequestDTO Документ о перемещении товаров
     */
    void processMovement(MovementDocumentRequestDTO movementDocumentRequestDTO);
}
