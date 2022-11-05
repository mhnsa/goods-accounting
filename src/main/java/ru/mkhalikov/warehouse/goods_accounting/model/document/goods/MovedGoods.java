package ru.mkhalikov.warehouse.goods_accounting.model;

import lombok.*;

import javax.persistence.*;

/**
 * Перемещенный товар
 */
@Entity
@Table(name = "moved_goods")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovedGoods {

    @Id
    @SequenceGenerator(name = "moved_goods_seq", sequenceName = "goods_accounting.moved_goods_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "article")
    private String article;

    @Column(name = "name")
    private String name;

    @Column(name = "quantity")
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "movement_document_id", referencedColumnName = "id")
    private MovementDocument movementDocument;

}
