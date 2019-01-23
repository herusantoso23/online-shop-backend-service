package com.herusantoso.tokonezia.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserDetailDTO extends IdDTO{

    @NotBlank
    private String username;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String fullname;

    @Size(max = 12)
    @JsonProperty(value = "phone_number")
    private String phoneNumber;

    @Size(max = 1000)
    @JsonProperty(value = "profile_image")
    private String profileImage;
}
