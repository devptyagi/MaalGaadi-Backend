package com.devtyagi.maalgaadi.dto.response;

import com.devtyagi.maalgaadi.dao.Driver;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginDriverResponseDTO {

    private Driver driver;
    private String accessToken;

}
