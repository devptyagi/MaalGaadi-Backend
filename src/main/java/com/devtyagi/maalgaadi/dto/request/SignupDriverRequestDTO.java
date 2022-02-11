package com.devtyagi.maalgaadi.dto.request;

import com.devtyagi.maalgaadi.dao.Route;
import lombok.Data;

import java.util.List;

@Data
public class SignupDriverRequestDTO {

    private String username;
    private String name;
    private String email;
    private String mobileNumber;
    private String password;
    private Integer age;
    private String truckNumber;
    private Integer truckCapacity;
    private String transporterName;
    private Double drivingExperience;
    private List<Route> interestedRoutes;

}
