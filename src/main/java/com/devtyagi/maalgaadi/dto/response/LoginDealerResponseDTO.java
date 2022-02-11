package com.devtyagi.maalgaadi.dto.response;

import com.devtyagi.maalgaadi.dao.Dealer;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginDealerResponseDTO {

    private Dealer dealer;
    private String accessToken;

}
