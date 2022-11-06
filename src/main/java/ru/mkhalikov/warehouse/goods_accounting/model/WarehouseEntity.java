package ru.mkhalikov.warehouse.goods_accounting.model;


import lombok.*;
import ru.mkhalikov.warehouse.goods_accounting.model.document.ReceiptDocument;
import ru.mkhalikov.warehouse.goods_accounting.model.document.SaleDocument;

import javax.persistence.*;
import java.util.Set;

/**
 * Склад
 */
@Entity
@Table(schema = "goods_accounting", name = "warehouse")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseEntity extends BaseEntity<Long> {

    @Id
    @SequenceGenerator(name = "warehouse_seq", sequenceName = "goods_accounting.warehouse_seq", allocationSize = 1)
    @GeneratedValue(generator = "warehouse_seq")
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProductEntity> products;

    @OneToMany(mappedBy = "destinationWarehouse", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ReceiptDocument> receiptDocuments;

    @OneToMany(mappedBy = "sourceWarehouse", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SaleDocument> saleDocuments;
}
