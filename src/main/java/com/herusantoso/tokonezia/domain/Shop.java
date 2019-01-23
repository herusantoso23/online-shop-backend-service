package com.herusantoso.tokonezia.domain;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name="shop")
public class Shop extends BaseDomain implements Serializable {

    @Column(length = 100)
    private String name;

    @Column(length = 100)
    private String city;

    @ManyToOne
    @JoinColumn(name = "seller")
    private User seller;

    @PrePersist
    public void prePersist(){
        super.prePersist();
    }

}
