package com.herusantoso.tokonezia.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ShippingAddressDTO extends IdDTO {

    @NotBlank
    @Size(max = 100)
    @JsonProperty()
    private String name;

    @NotBlank
    @Size(max = 12)
    @JsonProperty(value = "phone_number")
    private String phoneNumber;

    @NotBlank
    @Size(max = 100)
    @JsonProperty(value = "province")
    private String province;

    @NotBlank
    @Size(max = 100)
    @JsonProperty(value = "city")
    private String city;

    @NotBlank
    @Size(max = 100)
    @JsonProperty(value = "district")
    private String district;

    @NotBlank
    @Size(max = 100)
    @JsonProperty(value = "village")
    private String village;

    @NotNull
    @JsonProperty(value = "zip_code")
    private Integer zipCode;

    @NotBlank
    @Size(max = 254)
    @JsonProperty(value = "address")
    private String address;

}
