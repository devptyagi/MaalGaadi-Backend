package com.devtyagi.maalgaadi.dto.request;

import lombok.Data;

@Data
public class GetDriversForDealerRequestDTO {

    private String city;
    private Integer pageNumber;
    private Integer pageSize;
    private String sortBy = "drivingExperience";
    private Boolean descending = false;

}
