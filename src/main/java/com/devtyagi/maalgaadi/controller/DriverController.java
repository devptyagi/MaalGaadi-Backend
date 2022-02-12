package com.devtyagi.maalgaadi.controller;

import com.devtyagi.maalgaadi.constants.Endpoints;
import com.devtyagi.maalgaadi.dto.request.GetBookingsRequestDTO;
import com.devtyagi.maalgaadi.dto.request.LoginRequestDTO;
import com.devtyagi.maalgaadi.dto.request.LoginViaOtpRequestDTO;
import com.devtyagi.maalgaadi.dto.request.SignupDriverRequestDTO;
import com.devtyagi.maalgaadi.dto.response.GetBookingsResponseDTO;
import com.devtyagi.maalgaadi.dto.response.LoginDriverResponseDTO;
import com.devtyagi.maalgaadi.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(Endpoints.BASE_URL)
public class DriverController {

    private final DriverService driverService;

    @PostMapping(Endpoints.AuthAPI.DRIVER_SIGNUP)
    public LoginDriverResponseDTO signupDriver(@RequestBody @Valid SignupDriverRequestDTO signupDriverRequestDTO) {
        return driverService.signup(signupDriverRequestDTO);
    }

    @PostMapping(Endpoints.AuthAPI.DRIVER_LOGIN)
    public LoginDriverResponseDTO loginDriver(@RequestBody @Valid LoginRequestDTO loginRequestDTO) {
        return driverService.login(loginRequestDTO);
    }

    @PostMapping(Endpoints.AuthAPI.DRIVER_LOGIN_OTP)
    public LoginDriverResponseDTO loginDriverViaOtp(@RequestBody @Valid LoginViaOtpRequestDTO loginViaOtpRequestDTO) {
        return driverService.loginViaOtp(loginViaOtpRequestDTO.getUsername(), loginViaOtpRequestDTO.getOtp());
    }

    @PostMapping(Endpoints.DriverAPI.GET_BOOKINGS)
    public GetBookingsResponseDTO getBookingsForDriver(@RequestBody @Valid GetBookingsRequestDTO getBookingsRequestDTO) {
        return driverService.getBookingsForDriver(getBookingsRequestDTO);
    }

}
