package com.herusantoso.tokonezia.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CartDTO {

    @NotBlank
    @JsonProperty(value = "product_id")
    private String productId;

}
