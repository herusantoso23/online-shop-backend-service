package com.herusantoso.tokonezia.domain;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
@Table(name="cart")
public class Cart extends BaseDomain implements Serializable {

    @ManyToOne
    @JoinColumn(name = "buyer")
    private User buyer;

    @ManyToOne
    @JoinColumn(name = "product")
    private Product product;

    @Column
    private Boolean purchase;

    @PrePersist
    public void prePersist(){
        super.prePersist();
        this.purchase = Boolean.FALSE;
    }

}
