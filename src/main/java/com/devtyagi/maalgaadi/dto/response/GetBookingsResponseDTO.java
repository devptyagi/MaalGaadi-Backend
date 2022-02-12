package com.devtyagi.maalgaadi.dto.response;

import com.devtyagi.maalgaadi.dao.Booking;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetBookingsResponseDTO {

    private Long totalBookings;
    private Integer totalPages;
    private List<Booking> bookingList;

}
