package ru.mkhalikov.warehouse.goods_accounting.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Поступивший товар
 */
@Entity
@Table(name = "received_product")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReceivedGoods extends BaseEntity<Integer> {

    @Id
    @SequenceGenerator(name = "received_product_seq", sequenceName = "goods_accounting.received_product_seq", allocationSize = 1)
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
