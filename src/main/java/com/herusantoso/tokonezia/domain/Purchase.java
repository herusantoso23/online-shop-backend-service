package com.herusantoso.tokonezia.domain;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
@Table(name="purchase")
public class Purchase extends BaseDomain implements Serializable {

    @ManyToOne
    @JoinColumn(name = "shipping_address")
    private ShippingAddress shippingAddress;

    @ManyToOne
    @JoinColumn(name = "buyer")
    private User buyer;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @ManyToOne
    @JoinColumn(name = "payment")
    private Payment payment;

    @PrePersist
    public void prePersist(){
        super.prePersist();
    }

}
