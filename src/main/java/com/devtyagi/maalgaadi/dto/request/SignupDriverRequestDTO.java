package com.devtyagi.maalgaadi.dto.request;

import com.devtyagi.maalgaadi.dao.Route;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class SignupDriverRequestDTO {

    @NotBlank(message = "Username must not be blank!")
    private String username;

    @NotBlank(message = "Name must not be blank!")
    private String name;

    @NotBlank(message = "Email must not be blank!")
    @Pattern(
            regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$",
            message = "Invalid Email!"
    )
    private String email;

    @NotBlank(message = "Mobile Number must not be blank!")
    @Pattern(
            regexp = "^(?:(?:\\+|0{0,2})91(\\s*[\\-]\\s*)?|[0]?)?[789]\\d{9}$",
            message = "Invalid Mobile Number!"
    )
    private String mobileNumber;

    @NotBlank(message = "Password must not be blank!")
    private String password;

    @Min(value = 18)
    private Integer age;

    @NotBlank(message = "Truck Number must not be blank!")
    @Pattern(
            regexp = "^[A-Z]{2}[ -][0-9]{1,2}(?: [A-Z])?(?: [A-Z]*)? [0-9]{4}$",
            message = "Invalid Truck Number!"
    )
    private String truckNumber;

    private Integer truckCapacity;

    private String transporterName;

    @Min(value = 0)
    private Double drivingExperience;

    @Size(min = 3, max = 3)
    @Valid
    private List<Route> interestedRoutes;

}
