package ru.mkhalikov.warehouse.goods_accounting.model.document.goods;

import lombok.*;
import ru.mkhalikov.warehouse.goods_accounting.model.BaseEntity;
import ru.mkhalikov.warehouse.goods_accounting.model.document.ReceiptDocument;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Поступившие товары
 */
@Entity
@Table(schema = "goods_accounting", name = "received_goods")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReceivedGoodsEntity extends BaseEntity<Integer> {

    @Id
    @SequenceGenerator(name = "received_goods_seq", sequenceName = "goods_accounting.received_goods_seq", allocationSize = 1)
    @GeneratedValue(generator = "received_goods_seq")
    @Column(name = "id")
    private Integer id;

    @Column(name = "article")
    private String article;

    @Column(name = "name")
    private String name;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "purchase_price")
    private BigDecimal purchasePrice;

    @ManyToOne
    @JoinColumn(name = "receipt_document_id", referencedColumnName = "id")
    private ReceiptDocument receiptDocument;

}
