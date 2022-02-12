package com.devtyagi.maalgaadi.dto.request;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class GetDriversByRouteRequestDTO {

    @NotBlank(message = "From City must not be blank!")
    private String fromCity;

    @NotBlank(message = "To City must not be blank!")
    private String toCity;

    @Min(value = 0)
    private Integer pageNumber;

    @Min(value = 10)
    private Integer pageSize;
    private String sortBy = "drivingExperience";
    private Boolean descending = false;

}
