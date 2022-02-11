package com.devtyagi.maalgaadi.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class BookingRequestDTO {

    private String driverId;
    private String dealerId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date bookingDate;
    private String fromCity;
    private String toCity;

}
