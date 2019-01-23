package com.herusantoso.tokonezia.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class ShopDetailDTO extends IdDTO{

    @NotBlank
    @Size(max = 100)
    private String name;

    @Column(length = 100)
    private String city;

}
