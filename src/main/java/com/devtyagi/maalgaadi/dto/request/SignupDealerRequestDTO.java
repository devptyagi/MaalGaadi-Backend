package com.devtyagi.maalgaadi.dto.request;

import lombok.Data;

@Data
public class SignupDealerRequestDTO {

    private String username;
    private String name;
    private String email;
    private String mobileNumber;
    private String password;
    private String natureOfMaterial;
    private Double weightOfMaterial;
    private Integer quantity;
    private String city;
    private String state;

}
