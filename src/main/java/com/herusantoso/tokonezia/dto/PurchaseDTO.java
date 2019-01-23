package com.herusantoso.tokonezia.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
public class PurchaseDTO {

    @NotNull
    @Valid
    @JsonProperty(value = "shipping_address")
    private ShippingAddressDTO shippingAddress;

    @NotNull
    @Valid
    @JsonProperty(value = "purchase_detail")
    private List<PurchaseDetailDTO> purchaseDetails;

    @NotBlank
    private String paymentMethode;

    @NotNull
    private BigDecimal totalPrice;

}
