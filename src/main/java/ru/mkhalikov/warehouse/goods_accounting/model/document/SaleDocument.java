package ru.mkhalikov.warehouse.goods_accounting.model.document;


import lombok.*;
import ru.mkhalikov.warehouse.goods_accounting.model.BaseEntity;
import ru.mkhalikov.warehouse.goods_accounting.model.document.goods.SoldGoodsEntity;
import ru.mkhalikov.warehouse.goods_accounting.model.WarehouseEntity;

import javax.persistence.*;
import java.util.Set;

/**
 * Документ о продаже товаров
 */
@Entity
@Table(schema = "goods_accounting", name = "sale_document")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleDocument extends BaseEntity<Integer> {

    @Id
    @SequenceGenerator(name = "sale_document_seq", sequenceName = "goods_accounting.sale_document_seq", allocationSize = 1)
    @GeneratedValue(generator = "sale_document_seq")
    @Column(name = "id")
    private Integer id;

    @Column(name = "sale_number")
    private String saleNumber;

    @ManyToOne
    @JoinColumn(name = "source_warehouse_id", foreignKey = @ForeignKey(name = "fk_sale_document_source_warehouse_id"))
    private WarehouseEntity sourceWarehouse;

    @OneToMany(mappedBy = "saleDocument", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SoldGoodsEntity> products;

}
