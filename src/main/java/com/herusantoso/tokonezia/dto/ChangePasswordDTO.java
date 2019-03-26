package com.herusantoso.tokonezia.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ChangePasswordDTO {

    @NotBlank
    @JsonProperty(value = "old_password")
    private String oldPassword;

    @NotBlank
    @JsonProperty(value = "new_password")
    private String newPassord;

    @NotBlank
    @JsonProperty(value = "confirm_password")
    private String confirmPassword;

}
