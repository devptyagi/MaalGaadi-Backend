package com.devtyagi.maalgaadi.controller;

import com.devtyagi.maalgaadi.constants.Endpoints;
import com.devtyagi.maalgaadi.dto.request.LoginRequestDTO;
import com.devtyagi.maalgaadi.dto.request.SignupDriverRequestDTO;
import com.devtyagi.maalgaadi.dto.response.LoginDriverResponseDTO;
import com.devtyagi.maalgaadi.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
