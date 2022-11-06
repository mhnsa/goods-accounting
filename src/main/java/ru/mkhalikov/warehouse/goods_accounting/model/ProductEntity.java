package ru.mkhalikov.warehouse.goods_accounting.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Товар на складе
 */
@Entity
@Table(schema = "goods_accounting",name = "product")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity extends BaseEntity<Integer> {

    @Id
    @SequenceGenerator(name = "product_seq", sequenceName = "goods_accounting.product_seq", allocationSize = 1)
    @GeneratedValue(generator = "product_seq")
    @Column(name = "id")
    private Integer id;

    @Column(name = "article")
    private String article;

    @Column(name = "name")
    private String name;

    @Column(name = "last_purchase_price")
    private BigDecimal lastPurchasePrice;

    @Column(name = "last_sale_price")
    private BigDecimal lastSalePrice;

    @ManyToOne
    @JoinColumn(name = "warehouse_id", foreignKey = @ForeignKey(name = "fk_product_warehouse"))
    private WarehouseEntity warehouse;

    @Column(name = "quantity")
    private Integer quantity;

}
