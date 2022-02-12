package com.devtyagi.maalgaadi.dto.request;

import lombok.Data;

@Data
public class GetBookingsRequestDTO {

    private String driverId;
    private Integer pageNumber;
    private Integer pageSize;
    private String sortBy = "bookingDate";
    private Boolean descending = true;

}
