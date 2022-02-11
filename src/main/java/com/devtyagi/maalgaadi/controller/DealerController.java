package com.devtyagi.maalgaadi.controller;

import com.devtyagi.maalgaadi.constants.Endpoints;
import com.devtyagi.maalgaadi.dto.request.GetDriversForDealerRequestDTO;
import com.devtyagi.maalgaadi.dto.request.SignupDealerRequestDTO;
import com.devtyagi.maalgaadi.dto.response.GetDriversForDealerResponseDTO;
import com.devtyagi.maalgaadi.dto.response.LoginDealerResponseDTO;
import com.devtyagi.maalgaadi.service.DealerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(Endpoints.BASE_URL)
public class DealerController {

    private final DealerService dealerService;

    @PostMapping(Endpoints.AuthAPI.DEALER_SIGNUP)
    public LoginDealerResponseDTO signupDealer(@RequestBody SignupDealerRequestDTO signupDealerRequestDTO) {
        return dealerService.signup(signupDealerRequestDTO);
    }

    @PostMapping(Endpoints.DealerAPI.GET_DRIVERS)
    public GetDriversForDealerResponseDTO getDriversForDealer(@RequestBody GetDriversForDealerRequestDTO getDriversForDealerRequestDTO) {
        return dealerService.getDriversForDealer(getDriversForDealerRequestDTO);
    }

}
