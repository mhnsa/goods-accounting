package ru.mkhalikov.warehouse.goods_accounting.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Проданный товар
 */
@Entity
@Table
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SoldGoods {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
