package com.herusantoso.tokonezia.domain;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name="shipping_address")
public class ShippingAddress extends BaseDomain implements Serializable {

    @Column(length = 100)
    private String name;

    @Column(name = "phone_number", length = 12)
    private String phoneNumber;

    @Column(name = "province", length = 100)
    private String province;

    @Column(name = "city", length = 100)
    private String city;

    @Column(name = "district", length = 100)
    private String district;

    @Column(name = "village", length = 100)
    private String village;

    @Column(name = "zip_code", length = 5)
    private Integer zipCode;

    @Column(name = "address", length = 254)
    private String address;

    @ManyToOne
    @JoinColumn(name = "buyer")
    private User buyer;

    @PrePersist
    public void prePersist(){
        super.prePersist();
    }
}
