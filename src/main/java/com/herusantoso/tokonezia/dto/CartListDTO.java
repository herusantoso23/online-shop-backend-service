package com.herusantoso.tokonezia.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class CartListDTO {

    @JsonProperty("id")
    private String id;

    @JsonProperty("product")
    private ProductListDTO product;

}
