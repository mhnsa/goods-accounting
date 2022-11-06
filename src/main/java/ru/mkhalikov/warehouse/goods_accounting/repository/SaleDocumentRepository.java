package ru.mkhalikov.warehouse.goods_accounting.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mkhalikov.warehouse.goods_accounting.model.document.ReceiptDocument;
import ru.mkhalikov.warehouse.goods_accounting.model.document.SaleDocument;

@Repository
public interface SaleDocumentRepository extends CrudRepository<SaleDocument, Integer> {

}
