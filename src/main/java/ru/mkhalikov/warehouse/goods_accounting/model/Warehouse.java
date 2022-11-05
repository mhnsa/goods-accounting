package ru.mkhalikov.warehouse.goods_accounting.model;


import lombok.*;

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
public class Warehouse extends BaseEntity<Long> {

    @Id
    @SequenceGenerator(name = "warehouse_seq", sequenceName = "goods_accounting.warehouse_seq", allocationSize = 1)
    @GeneratedValue(generator = "warehouse_seq")
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "warehouse")
    private Set<Product> products;

}
