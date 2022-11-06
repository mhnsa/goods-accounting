package ru.mkhalikov.warehouse.goods_accounting.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mkhalikov.warehouse.goods_accounting.model.document.goods.ReceivedGoodsEntity;
import ru.mkhalikov.warehouse.goods_accounting.model.document.goods.SoldGoodsEntity;

@Repository
public interface SoldGoodsRepository extends CrudRepository<SoldGoodsEntity, Integer> {

}
