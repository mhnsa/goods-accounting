package ru.mkhalikov.warehouse.goods_accounting.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mkhalikov.warehouse.goods_accounting.model.document.goods.MovedGoodsEntity;

@Repository
public interface MovedGoodsRepository extends CrudRepository<MovedGoodsEntity, Integer> {

}
