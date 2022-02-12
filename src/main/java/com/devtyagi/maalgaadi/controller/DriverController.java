package com.devtyagi.maalgaadi.controller;

import com.devtyagi.maalgaadi.constants.Endpoints;
import com.devtyagi.maalgaadi.dao.Booking;
import com.devtyagi.maalgaadi.dto.request.GetBookingsRequestDTO;
import com.devtyagi.maalgaadi.dto.request.LoginRequestDTO;
import com.devtyagi.maalgaadi.dto.request.SignupDriverRequestDTO;
import com.devtyagi.maalgaadi.dto.response.GetBookingsResponseDTO;
import com.devtyagi.maalgaadi.dto.response.LoginDriverResponseDTO;
import com.devtyagi.maalgaadi.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(Endpoints.BASE_URL)
public class DriverController {

    private final DriverService driverService;

    @PostMapping(Endpoints.AuthAPI.DRIVER_SIGNUP)
    public LoginDriverResponseDTO signupDriver(@RequestBody SignupDriverRequestDTO signupDriverRequestDTO) {
        return driverService.signup(signupDriverRequestDTO);
    }

    @PostMapping(Endpoints.AuthAPI.DRIVER_LOGIN)
    public LoginDriverResponseDTO loginDriver(@RequestBody LoginRequestDTO loginRequestDTO) {
        return driverService.login(loginRequestDTO);
    }

    @PostMapping(Endpoints.DriverAPI.GET_BOOKINGS)
    public GetBookingsResponseDTO getBookingsForDriver(@RequestBody GetBookingsRequestDTO getBookingsRequestDTO) {
        return driverService.getBookingsForDriver(getBookingsRequestDTO);
    }

}
