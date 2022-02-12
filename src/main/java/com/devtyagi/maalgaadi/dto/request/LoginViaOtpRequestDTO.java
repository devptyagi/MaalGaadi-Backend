package com.devtyagi.maalgaadi.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginViaOtpRequestDTO {

    @NotBlank(message = "Username must not be blank!")
    private String username;

    @NotBlank(message = "OTP not be blank!")
    private Integer otp;

}
