package com.devtyagi.maalgaadi.dto.request;

import lombok.Data;

@Data
public class LoginViaOtpRequestDTO {

    private String username;
    private Integer otp;

}
