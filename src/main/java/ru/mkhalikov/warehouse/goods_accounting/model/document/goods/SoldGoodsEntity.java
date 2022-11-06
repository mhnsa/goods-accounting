package ru.mkhalikov.warehouse.goods_accounting.model.document.goods;

import lombok.*;
import ru.mkhalikov.warehouse.goods_accounting.model.BaseEntity;
import ru.mkhalikov.warehouse.goods_accounting.model.document.SaleDocument;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Проданный товар
 */
@Entity
@Table(schema = "goods_accounting", name = "sold_goods")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SoldGoodsEntity extends BaseEntity<Integer> {

    @Id
    @SequenceGenerator(name = "sold_goods_seq", sequenceName = "goods_accounting.sold_goods_seq", allocationSize = 1)
    @GeneratedValue(generator = "sold_goods_seq")
    @Column(name = "id")
    private long id;

    @Column(name = "article")
    private String article;

    @Column(name = "name")
    private String name;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "sale_price")
    private BigDecimal salePrice;

    @ManyToOne
    @JoinColumn(name = "sale_document_id", referencedColumnName = "id")
    private SaleDocument saleDocument;

}
