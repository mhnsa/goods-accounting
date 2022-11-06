package ru.mkhalikov.warehouse.goods_accounting.service;

import ru.mkhalikov.warehouse.goods_accounting.dto.request.SaleDocumentRequestDTO;

/**
 * Сервис для работы с продажей товаров
 **/
public interface SaleService {

    /**
     * Метод обработки документов о продаже товаров
     *
     * @param saleDocumentRequestDTO Документ о продаже товаров
     */
    void processSale(SaleDocumentRequestDTO saleDocumentRequestDTO);
}
