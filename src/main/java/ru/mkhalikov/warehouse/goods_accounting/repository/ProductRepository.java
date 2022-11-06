package ru.mkhalikov.warehouse.goods_accounting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mkhalikov.warehouse.goods_accounting.model.ProductEntity;
import ru.mkhalikov.warehouse.goods_accounting.model.WarehouseEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {

    @Override
    List<ProductEntity> findAll();

    Optional<ProductEntity> findByArticleAndWarehouse(String article, WarehouseEntity warehouse);
}
