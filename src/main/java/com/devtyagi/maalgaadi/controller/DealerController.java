package com.devtyagi.maalgaadi.controller;

import com.devtyagi.maalgaadi.constants.Endpoints;
import com.devtyagi.maalgaadi.dto.request.*;
import com.devtyagi.maalgaadi.dto.response.BookingResponseDTO;
import com.devtyagi.maalgaadi.dto.response.GetDriversResponseDTO;
import com.devtyagi.maalgaadi.dto.response.LoginDealerResponseDTO;
import com.devtyagi.maalgaadi.service.DealerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(Endpoints.BASE_URL)
@Slf4j
public class DealerController {

    private final DealerService dealerService;

    @PostMapping(Endpoints.AuthAPI.DEALER_SIGNUP)
    public LoginDealerResponseDTO signupDealer(@RequestBody @Valid SignupDealerRequestDTO signupDealerRequestDTO) {
        return dealerService.signup(signupDealerRequestDTO);
    }

    @PostMapping(Endpoints.AuthAPI.DEALER_LOGIN)
    public LoginDealerResponseDTO loginDealer(@RequestBody @Valid LoginRequestDTO loginRequestDTO) {
        return dealerService.login(loginRequestDTO);
    }

    @PostMapping(Endpoints.AuthAPI.DEALER_LOGIN_OTP)
    public LoginDealerResponseDTO loginDealerViaOtp(@RequestBody @Valid LoginViaOtpRequestDTO loginViaOtpRequestDTO) {
        return dealerService.loginViaOtp(loginViaOtpRequestDTO.getUsername(), loginViaOtpRequestDTO.getOtp());
    }

    @PostMapping(Endpoints.DealerAPI.GET_DRIVERS)
    public GetDriversResponseDTO getDriversForDealer(@RequestBody @Valid GetDriversForDealerRequestDTO getDriversForDealerRequestDTO) {
        return dealerService.getDriversForDealer(getDriversForDealerRequestDTO);
    }

    @PostMapping(Endpoints.DealerAPI.GET_DRIVERS_BY_STATE)
    public GetDriversResponseDTO getDriversByState(@RequestBody @Valid GetDriversByStateRequestDTO getDriversByStateRequestDTO) {
        return dealerService.getDriversByState(getDriversByStateRequestDTO);
    }

    @PostMapping(Endpoints.DealerAPI.GET_DRIVERS_BY_ROUTE)
    public GetDriversResponseDTO getDriversByRoute(@RequestBody @Valid GetDriversByRouteRequestDTO getDriversByRouteRequestDTO) {
        return dealerService.getDriversByRoute(getDriversByRouteRequestDTO);
    }

    @PostMapping(Endpoints.DealerAPI.BOOK_DRIVER)
    public BookingResponseDTO bookDriver(@RequestBody @Valid BookingRequestDTO bookingRequestDTO) {
        return dealerService.bookDriver(bookingRequestDTO);
    }

}
