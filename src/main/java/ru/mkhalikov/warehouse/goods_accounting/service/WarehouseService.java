package ru.mkhalikov.warehouse.goods_accounting.service;

import ru.mkhalikov.warehouse.goods_accounting.dto.request.WarehouseRequestDTO;
import ru.mkhalikov.warehouse.goods_accounting.dto.response.WarehouseResponseDTO;

import java.util.List;

/**
 * Сервис для работы с информацией о складе
 **/
public interface WarehouseService {
    /**
     * Добавление записи в таблицу информации о складах
     *
     * @param warehouseRequestDTO Модель информации о складе (запрос)
     * @return Идентификатор добавленной записи
     */
    Integer addWarehouse(WarehouseRequestDTO warehouseRequestDTO);

    /**
     * Получение информации о складе по идентификатору
     *
     * @param id Идентификатор склада
     * @return Информация о складе
     */
    WarehouseResponseDTO getById(Integer id);

    /**
     * Получение информации о всех складах в БД
     *
     * @return Список заявлений на аккредитацию
     */
    List<WarehouseResponseDTO> getAll();

    /**
     * Удаление информации о складе по идентификатору
     *
     * @param id Идентификатор склада
     */
    void deleteById(Integer id);
}
