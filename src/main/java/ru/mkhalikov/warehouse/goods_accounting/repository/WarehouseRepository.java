package ru.mkhalikov.warehouse.goods_accounting.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mkhalikov.warehouse.goods_accounting.model.WarehouseEntity;

import java.util.List;

@Repository
public interface WarehouseRepository extends CrudRepository<WarehouseEntity, Integer> {

    @Override
    List<WarehouseEntity> findAll();
}
