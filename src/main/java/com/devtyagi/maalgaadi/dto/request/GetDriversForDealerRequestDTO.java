package com.devtyagi.maalgaadi.dto.request;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class GetDriversForDealerRequestDTO {

    @NotBlank(message = "City must not be blank!")
    private String city;

    @Min(value = 0)
    private Integer pageNumber;

    @Min(value = 10)
    private Integer pageSize;
    private String sortBy = "drivingExperience";
    private Boolean descending = false;

}
