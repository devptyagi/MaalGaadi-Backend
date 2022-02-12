package com.devtyagi.maalgaadi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class LoginViaOtpRequestDTO {

    @NotBlank(message = "Username must not be blank!")
    private String username;

    @Min(value = 100000, message = "OTP Must be 6 digits")
    @Max(value = 900000, message = "OTP Must be 6 digits")
    private Integer otp;

}
