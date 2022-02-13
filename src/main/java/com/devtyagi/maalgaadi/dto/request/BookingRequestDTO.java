package com.devtyagi.maalgaadi.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class BookingRequestDTO {

    @NotBlank(message = "Driver ID must not be blank!")
    private String driverId;

    @NotBlank(message = "Dealer ID must not be blank!")
    private String dealerId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date bookingDate;

    @NotBlank(message = "From City must not be blank!")
    private String fromCity;

    @NotBlank(message = "To City must not be blank!")
    private String toCity;

}
