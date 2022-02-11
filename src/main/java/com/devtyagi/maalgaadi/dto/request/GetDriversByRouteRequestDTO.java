package com.devtyagi.maalgaadi.dto.request;

import lombok.Data;

@Data
public class GetDriversByRouteRequestDTO {

    private String fromCity;
    private String toCity;
    private Integer pageNumber;
    private Integer pageSize;
    private String sortBy = "drivingExperience";
    private Boolean descending = false;

}
