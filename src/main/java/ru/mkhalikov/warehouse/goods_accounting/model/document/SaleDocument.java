package ru.mkhalikov.warehouse.goods_accounting.model.document;


import lombok.*;
import ru.mkhalikov.warehouse.goods_accounting.model.document.goods.SoldGoods;
import ru.mkhalikov.warehouse.goods_accounting.model.Warehouse;

import javax.persistence.*;
import java.util.Set;

/**
 * Документ о продаже товаров
 */
@Entity
@Table(name = "sale_document")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "sale_number")
    private String saleNumber;

    @ManyToOne
    private Warehouse sourceWarehouse;

    @OneToMany(mappedBy = "saleDocument")
    private Set<SoldGoods> products;

}
