package com.herusantoso.tokonezia.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class VirtualAccountDTO {

    @JsonProperty(value = "payment_expired_date")
    private Long paymentExpiredDate;

    @JsonProperty(value = "amount")
    private BigDecimal amount;

    @JsonProperty(value = "virtual_account")
    private String virtualAccount;

}
