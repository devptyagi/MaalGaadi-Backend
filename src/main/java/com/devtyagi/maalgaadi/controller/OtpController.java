package com.devtyagi.maalgaadi.controller;

import com.devtyagi.maalgaadi.constants.Endpoints;
import com.devtyagi.maalgaadi.service.OtpService;
import com.devtyagi.maalgaadi.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(Endpoints.BASE_URL)
@RequiredArgsConstructor
public class OtpController {

    private final OtpService otpService;

    private final UserService userService;

    @GetMapping(Endpoints.AuthAPI.GET_OTP)
    public String generateOtp(@RequestParam String username) throws IOException {
        val user = userService.getUserByUsername(username);
        otpService.generateOTP(user);
        return "Sent OTP";
    }

}
