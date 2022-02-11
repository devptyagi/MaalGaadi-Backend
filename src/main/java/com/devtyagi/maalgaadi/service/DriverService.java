package com.devtyagi.maalgaadi.service;

import com.devtyagi.maalgaadi.dao.Driver;
import com.devtyagi.maalgaadi.dao.User;
import com.devtyagi.maalgaadi.dto.request.LoginRequestDTO;
import com.devtyagi.maalgaadi.dto.request.SignupDriverRequestDTO;
import com.devtyagi.maalgaadi.dto.response.LoginDriverResponseDTO;
import com.devtyagi.maalgaadi.enums.UserRole;
import com.devtyagi.maalgaadi.exception.DriverNotFoundException;
import com.devtyagi.maalgaadi.exception.InvalidCredentialsException;
import com.devtyagi.maalgaadi.model.CustomUserDetails;
import com.devtyagi.maalgaadi.repository.DealerRepository;
import com.devtyagi.maalgaadi.repository.DriverRepository;
import com.devtyagi.maalgaadi.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DriverService {

    private final BCryptPasswordEncoder passwordEncoder;

    private final DriverRepository driverRepository;

    private final AuthenticationManager authenticationManager;

    private final JwtUserDetailsService userDetailsService;

    private final JwtUtil jwtUtil;

    public LoginDriverResponseDTO signup(SignupDriverRequestDTO signupRequest) {
        val user = User.builder()
                .name(signupRequest.getName())
                .email(signupRequest.getEmail())
                .username(signupRequest.getUsername())
                .mobileNumber(signupRequest.getMobileNumber())
                .password(passwordEncoder.encode(signupRequest.getPassword()))
                .role(UserRole.DRIVER)
                .build();
        val driver = Driver.builder()
                .user(user)
                .age(signupRequest.getAge())
                .truckNumber(signupRequest.getTruckNumber())
                .truckCapacity(signupRequest.getTruckCapacity())
                .transporterName(signupRequest.getTransporterName())
                .drivingExperience(signupRequest.getDrivingExperience())
                .interestedRoutes(signupRequest.getInterestedRoutes())
                .build();
        driverRepository.save(driver);
        return login(new LoginRequestDTO(signupRequest.getUsername(), signupRequest.getPassword()));
    }

    public LoginDriverResponseDTO login(LoginRequestDTO loginRequestDTO) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequestDTO.getUsername(),
                            loginRequestDTO.getPassword()
                    )
            );
        } catch (BadCredentialsException exception) {
            throw new InvalidCredentialsException();
        }
        val userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(loginRequestDTO.getUsername());
        val accessToken = jwtUtil.generateToken(userDetails);
        val driver = driverRepository.findByUser_Username(userDetails.getUsername());
        return LoginDriverResponseDTO.builder()
                .driver(driver)
                .accessToken(accessToken)
                .build();
    }

    public Driver getDriverById(String id) {
        return driverRepository.findById(id)
                .orElseThrow(DriverNotFoundException::new);
    }

}
