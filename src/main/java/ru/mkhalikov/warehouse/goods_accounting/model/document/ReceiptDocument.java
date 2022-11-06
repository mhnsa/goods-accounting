package ru.mkhalikov.warehouse.goods_accounting.model.document;


import lombok.*;
import ru.mkhalikov.warehouse.goods_accounting.model.BaseEntity;
import ru.mkhalikov.warehouse.goods_accounting.model.WarehouseEntity;
import ru.mkhalikov.warehouse.goods_accounting.model.document.goods.ReceivedGoodsEntity;

import javax.persistence.*;
import java.util.Set;

/**
 * Документ о поступлении товаров
 */
@Entity
@Table(name = "receipt_document")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptDocument extends BaseEntity<Integer> {

    @Id
    @SequenceGenerator(name = "receipt_document_seq", sequenceName = "goods_accounting.receipt_document_seq", allocationSize = 1)
    @GeneratedValue(generator = "receipt_document_seq")
    @Column(name = "id")
    private Integer id;

    @Column(name = "receipt_number")
    private String receiptNumber;

    @ManyToOne
    @JoinColumn(name = "destination_warehouse_id", foreignKey = @ForeignKey(name = "fk_receipt_document_destination_warehouse_id"))
    private WarehouseEntity destinationWarehouse;

    @OneToMany(mappedBy = "receiptDocument")
    private Set<ReceivedGoodsEntity> receivedGoodEntities;

}
