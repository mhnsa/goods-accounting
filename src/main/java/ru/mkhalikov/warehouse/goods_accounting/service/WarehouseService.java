package ru.mkhalikov.warehouse.goods_accounting.service;

import ru.mkhalikov.warehouse.goods_accounting.dto.WarehouseDTO;

import java.util.List;

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
     * Получение информации о складе по идентификатору
     *
     * @param id Идентификатор склада
     * @return Информация о складе
     */
    WarehouseDTO getById(Integer id);

    /**
     * Получение информации о всех складах в БД
     *
     * @return Список заявлений на аккредитацию
     */
    List<WarehouseDTO> getAll();

    /**
     * Удаление информации о складе по идентификатору
     *
     * @param id Идентификатор склада
     */
    void deleteById(Integer id);
}
