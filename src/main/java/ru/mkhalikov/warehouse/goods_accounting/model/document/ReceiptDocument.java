package ru.mkhalikov.warehouse.goods_accounting.model;


import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * Накладная о поступлении товаров
 */
@Entity
@Table(name = "receipt_document")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "receipt_number")
    private String receiptNumber;

    @ManyToOne
    private Warehouse destinationWarehouse;

    @OneToMany(mappedBy = "receiptDocument")
    private Set<ReceivedGoods> receivedGoods;

}
