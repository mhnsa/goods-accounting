package ru.mkhalikov.warehouse.goods_accounting.service;

import ru.mkhalikov.warehouse.goods_accounting.dto.WarehouseDTO;

/**
 * Сервис для работы с информацией о складе
 **/
public interface WarehouseService {
    /**
     * Добавление записи в таблицу информации о складах
     *
     * @param warehouseDTO Критерий поиска
     * @return Идентификатор добавленной записи
     */
    Integer addWarehouse(WarehouseDTO warehouseDTO);

    /**
     * Получение списка заявок на аккредитацию
     *
     * @param id Идентификатор склада
     * @return Список заявлений на аккредитацию
     */
    WarehouseDTO getById(Integer id);
}
