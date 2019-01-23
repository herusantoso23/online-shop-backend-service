package com.herusantoso.tokonezia.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
public class ProductListDTO extends IdDTO {

    @NotBlank
    @Size(max = 100)
    @JsonProperty(value = "name")
    private String name;

    @NotBlank
    @Size(max = 20)
    @JsonProperty(value = "category")
    private String category;

    @NotNull
    @JsonProperty(value = "price")
    private BigDecimal price;

    @NotNull
    @JsonProperty(value = "quantity")
    private Long quantity;

    @NotNull
    @Size(max = 1000)
    @JsonProperty(value = "image")
    private String image;
}
