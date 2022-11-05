package ru.mkhalikov.warehouse.goods_accounting.model;


import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * Документ о перемещении товаров
 */
@Entity
@Table(name = "movement_document")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovementDocument {
    @Id
    @SequenceGenerator(name = "movement_document_seq", sequenceName = "goods_accounting.movement_document_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "movement_number")
    private String movementNumber;

    @ManyToOne
    @JoinColumn(name = "source_warehouse_id", foreignKey = @ForeignKey(name = "fk_movement_document_source_warehouse_id"))
    private Warehouse sourceWarehouse;

    @ManyToOne
    private Warehouse destinationWarehouse;

    @OneToMany(mappedBy = "warehouse")
    private Set<Product> products;

}
