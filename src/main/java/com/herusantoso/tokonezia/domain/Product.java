package com.herusantoso.tokonezia.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
@Table(name="product")
public class Product extends BaseDomain implements Serializable {

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "category", length = 20)
    private String category;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "sold")
    private Long sold;

    @Column(name = "image", length = 1000)
    private String image;

    @ManyToOne
    @JoinColumn(name = "shop")
    private Shop shop;

    @PrePersist
    public void prePersist(){
        super.prePersist();
        this.sold = 0L;
    }

}
