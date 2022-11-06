package ru.mkhalikov.warehouse.goods_accounting.model.document;


import lombok.*;
import ru.mkhalikov.warehouse.goods_accounting.model.BaseEntity;
import ru.mkhalikov.warehouse.goods_accounting.model.WarehouseEntity;
import ru.mkhalikov.warehouse.goods_accounting.model.document.goods.MovedGoodsEntity;

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
public class MovementDocumentEntity extends BaseEntity<Integer> {

    @Id
    @SequenceGenerator(name = "movement_document_seq", sequenceName = "goods_accounting.movement_document_seq", allocationSize = 1)
    @GeneratedValue(generator = "movement_document_seq")
    @Column(name = "id")
    private Integer id;

    @Column(name = "movement_number")
    private String movementNumber;

    @ManyToOne
    @JoinColumn(name = "source_warehouse_id", foreignKey = @ForeignKey(name = "fk_movement_document_source_warehouse_id"))
    private WarehouseEntity sourceWarehouse;

    @ManyToOne
    @JoinColumn(name = "destination_warehouse_id", foreignKey = @ForeignKey(name = "fk_movement_document_destination_warehouse_id"))
    private WarehouseEntity destinationWarehouse;

    @OneToMany(mappedBy = "movementDocument")
    private Set<MovedGoodsEntity> movedGoods;
}
