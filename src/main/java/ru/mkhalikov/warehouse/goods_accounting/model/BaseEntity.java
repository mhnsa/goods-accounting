package ru.mkhalikov.warehouse.goods_accounting.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity<I> implements Serializable {

    @Column(name = "created_dttm", nullable = false)
    private LocalDateTime createdDttm;

    @Column(name = "updated_dttm", nullable = false)
    private LocalDateTime updatedDttm;

    @PrePersist
    protected void prePersist() {
        var now = LocalDateTime.now();
        setCreatedDttm(now);
        setUpdatedDttm(now);
    }

    @PreUpdate
    protected void preUpdate() {
        setUpdatedDttm(LocalDateTime.now());
    }
}
