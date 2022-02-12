package com.devtyagi.maalgaadi.dto.request;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class GetBookingsRequestDTO {

    @NotBlank(message = "Driver ID must not be blank!")
    private String driverId;

    @Min(value = 0)
    private Integer pageNumber;

    @Min(value = 10)
    private Integer pageSize;
    private String sortBy = "bookingDate";
    private Boolean descending = true;

}
