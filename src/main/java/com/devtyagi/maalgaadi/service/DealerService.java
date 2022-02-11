package com.devtyagi.maalgaadi.service;

import com.devtyagi.maalgaadi.dao.Dealer;
import com.devtyagi.maalgaadi.dao.User;
import com.devtyagi.maalgaadi.dto.request.GetDriversForDealerRequestDTO;
import com.devtyagi.maalgaadi.dto.request.LoginRequestDTO;
import com.devtyagi.maalgaadi.dto.request.SignupDealerRequestDTO;
import com.devtyagi.maalgaadi.dto.response.GetDriversForDealerResponseDTO;
import com.devtyagi.maalgaadi.dto.response.LoginDealerResponseDTO;
import com.devtyagi.maalgaadi.enums.UserRole;
import com.devtyagi.maalgaadi.exception.InvalidCredentialsException;
import com.devtyagi.maalgaadi.exception.InvalidSortFieldException;
import com.devtyagi.maalgaadi.model.CustomUserDetails;
import com.devtyagi.maalgaadi.repository.DealerRepository;
import com.devtyagi.maalgaadi.repository.DriverRepository;
import com.devtyagi.maalgaadi.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DealerService {

    private final BCryptPasswordEncoder passwordEncoder;

    private final DealerRepository dealerRepository;

    private final AuthenticationManager authenticationManager;

    private final JwtUserDetailsService userDetailsService;

    private final DriverRepository driverRepository;

    private final JwtUtil jwtUtil;

    public LoginDealerResponseDTO signup(SignupDealerRequestDTO signupRequest) {
        val user = User.builder()
                .name(signupRequest.getName())
                .email(signupRequest.getEmail())
                .username(signupRequest.getUsername())
                .mobileNumber(signupRequest.getMobileNumber())
                .password(passwordEncoder.encode(signupRequest.getPassword()))
                .role(UserRole.DEALER)
                .build();

        val dealer = Dealer.builder()
                .user(user)
                .natureOfMaterial(signupRequest.getNatureOfMaterial())
                .quantity(signupRequest.getQuantity())
                .weightOfMaterial(signupRequest.getWeightOfMaterial())
                .city(signupRequest.getCity())
                .state(signupRequest.getState())
                .build();

       dealerRepository.save(dealer);

        return login(new LoginRequestDTO(signupRequest.getUsername(), signupRequest.getPassword()));
    }

    public LoginDealerResponseDTO login(LoginRequestDTO loginRequestDTO) {
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
        val dealer = dealerRepository.findByUser_Username(userDetails.getUsername());
        return LoginDealerResponseDTO.builder()
                .dealer(dealer)
                .accessToken(accessToken)
                .build();
    }

    public GetDriversForDealerResponseDTO getDriversForDealer(GetDriversForDealerRequestDTO driverRequest) {
        val sortOrder = driverRequest.getDescending() ? Sort.Direction.DESC : Sort.Direction.ASC;
        val sortBy = getSortField(driverRequest.getSortBy());
        val pageRequest = PageRequest.of(
                driverRequest.getPageNumber(),
                driverRequest.getPageSize(),
                Sort.by(sortOrder, sortBy)
        );
        val drivers = driverRepository.getAllDriversByCity(driverRequest.getCity(), pageRequest);
        return GetDriversForDealerResponseDTO.builder()
                .totalDrivers(drivers.getTotalElements())
                .totalPages(drivers.getTotalPages())
                .driverList(drivers.toList())
                .build();
    }

    private String getSortField(String sortRequest) {
        switch (sortRequest) {
            case "name": return "user.name";
            case "age": return "age";
            case "truckCapacity": return "truckCapacity";
            case "transporterName": return "transporterName";
            case "drivingExperience": return "drivingExperience";
            default: throw new InvalidSortFieldException(String.format("Can not sort on %s!", sortRequest));
        }
    }

}
