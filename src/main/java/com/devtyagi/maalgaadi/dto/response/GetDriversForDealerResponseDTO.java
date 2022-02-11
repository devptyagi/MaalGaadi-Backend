package com.devtyagi.maalgaadi.dto.response;

import com.devtyagi.maalgaadi.dao.Driver;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetDriversForDealerResponseDTO {

    private Long totalDrivers;
    private Integer totalPages;
    private List<Driver> driverList;

}
