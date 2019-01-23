package com.herusantoso.tokonezia.domain;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
@Table(name="purchase_detail")
public class PurchaseDetail extends BaseDomain implements Serializable {

    @ManyToOne
    @JoinColumn(name = "product")
    private Product product;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "courier", length = 100)
    private String courier;

    @Column(name = "notes", length = 256)
    private String notes;

    @ManyToOne
    @JoinColumn(name = "purchase")
    private Purchase purchase;

    @PrePersist
    public void prePersist(){
        super.prePersist();
    }

}
