package ru.mkhalikov.warehouse.goods_accounting.controller;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(schema = "warehouse")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "article")
    private String article;

    @Column(name = "name")
    private String name;

    @Column(name = "last_purchase_price")
    private BigDecimal lastPurchasePrice;

    @Column(name = "last_sale_price")
    private BigDecimal lastSalePrice;

}
