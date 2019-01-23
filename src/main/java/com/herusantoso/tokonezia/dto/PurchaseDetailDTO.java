package com.herusantoso.tokonezia.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class PurchaseDetailDTO {

    @JsonProperty(value = "cart_id")
    private String cartId;

    @JsonProperty(value = "quantity")
    private Long quantity;

    @Size(max = 100)
    @JsonProperty(value = "courier")
    private String courier;

    @Size(max = 256)
    @JsonProperty(value = "notes")
    private String notes;

}
