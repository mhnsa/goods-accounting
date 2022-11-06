package ru.mkhalikov.warehouse.goods_accounting.service;

import ru.mkhalikov.warehouse.goods_accounting.dto.request.ProductRequestDTO;
import ru.mkhalikov.warehouse.goods_accounting.dto.response.ProductResponseDTO;

import java.util.List;

/**
 * Сервис для работы с информацией о товарах на складе
 **/
public interface ProductService {


    /**
     * Добавление записи в таблицу товаров
     *
     * @param productRequestDTO Модель информации о товаре (запрос)
     * @return Идентификатор добавленной записи
     */
    Integer addProduct(ProductRequestDTO productRequestDTO);

    /**
     * Получение информации о всех товарах в БД
     *
     * @return Коллекция моделей информации о товарах
     */
    List<ProductResponseDTO> getAll();

    /**
     * Удаление информации о товаре по идентификатору
     *
     * @param id Идентификатор товара
     */
    void deleteById(Integer id);

    /**
     * Получение информации о товаре по идентификатору
     *
     * @param id Идентификатор товраа
     * @return Информация о товаре
     */
    ProductResponseDTO getById(Integer id);
}
