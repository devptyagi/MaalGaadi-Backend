package com.devtyagi.maalgaadi.service;

import com.devtyagi.maalgaadi.dao.Booking;
import com.devtyagi.maalgaadi.dao.Dealer;
import com.devtyagi.maalgaadi.dao.User;
import com.devtyagi.maalgaadi.dto.request.*;
import com.devtyagi.maalgaadi.dto.response.BookingResponseDTO;
import com.devtyagi.maalgaadi.dto.response.GetDriversResponseDTO;
import com.devtyagi.maalgaadi.dto.response.LoginDealerResponseDTO;
import com.devtyagi.maalgaadi.enums.UserRole;
import com.devtyagi.maalgaadi.exception.*;
import com.devtyagi.maalgaadi.model.CustomUserDetails;
import com.devtyagi.maalgaadi.repository.BookingRepository;
import com.devtyagi.maalgaadi.repository.DealerRepository;
import com.devtyagi.maalgaadi.repository.DriverRepository;
import com.devtyagi.maalgaadi.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class DealerService {

    private final BCryptPasswordEncoder passwordEncoder;

    private final DealerRepository dealerRepository;

    private final AuthenticationManager authenticationManager;

    private final JwtUserDetailsService userDetailsService;

    private final DriverRepository driverRepository;

    private final DriverService driverService;

    private final JwtUtil jwtUtil;

    private final BookingRepository bookingRepository;

    private final OtpService otpService;

    private final UserService userService;

    public LoginDealerResponseDTO signup(SignupDealerRequestDTO signupRequest) {
        if(userService.exists(signupRequest.getUsername())) {
            throw new UserAlreadyExistsException();
        }
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

    public GetDriversResponseDTO getDriversForDealer(GetDriversForDealerRequestDTO driverRequest) {
        val sortOrder = driverRequest.getDescending() ? Sort.Direction.DESC : Sort.Direction.ASC;
        val sortBy = getSortField(driverRequest.getSortBy());
        val pageRequest = PageRequest.of(
                driverRequest.getPageNumber(),
                driverRequest.getPageSize(),
                Sort.by(sortOrder, sortBy)
        );
        val drivers = driverRepository.getAllDriversByCity(driverRequest.getCity(), pageRequest);
        return GetDriversResponseDTO.builder()
                .totalDrivers(drivers.getTotalElements())
                .totalPages(drivers.getTotalPages())
                .driverList(drivers.toList())
                .build();
    }

    public GetDriversResponseDTO getDriversByState(GetDriversByStateRequestDTO driverRequest) {
        val sortOrder = driverRequest.getDescending() ? Sort.Direction.DESC : Sort.Direction.ASC;
        val sortBy = getSortField(driverRequest.getSortBy());
        val pageRequest = PageRequest.of(
                driverRequest.getPageNumber(),
                driverRequest.getPageSize(),
                Sort.by(sortOrder, sortBy)
        );
        val drivers = driverRepository.getAllDriversByState(driverRequest.getState(), pageRequest);
        return GetDriversResponseDTO.builder()
                .totalDrivers(drivers.getTotalElements())
                .totalPages(drivers.getTotalPages())
                .driverList(drivers.toList())
                .build();
    }

    public GetDriversResponseDTO getDriversByRoute(GetDriversByRouteRequestDTO driverRequest) {
        val sortOrder = driverRequest.getDescending() ? Sort.Direction.DESC : Sort.Direction.ASC;
        val sortBy = getSortField(driverRequest.getSortBy());
        val pageRequest = PageRequest.of(
                driverRequest.getPageNumber(),
                driverRequest.getPageSize(),
                Sort.by(sortOrder, sortBy)
        );
        val drivers = driverRepository.getAllDriversByRoute(driverRequest.getFromCity(), driverRequest.getToCity(), pageRequest);
        return GetDriversResponseDTO.builder()
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

    public Dealer getDealerById(String id) {
        return dealerRepository.findById(id)
                .orElseThrow(DealerNotFoundException::new);
    }

    public BookingResponseDTO bookDriver(BookingRequestDTO bookingRequestDTO) {
        val dealer = getDealerById(bookingRequestDTO.getDealerId());
        val driver = driverService.getDriverById(bookingRequestDTO.getDriverId());
        val booking = Booking.builder()
                .dealer(dealer)
                .driver(driver)
                .bookedOn(new Date())
                .fromCity(bookingRequestDTO.getFromCity())
                .toCity(bookingRequestDTO.getToCity())
                .bookingDate(bookingRequestDTO.getBookingDate())
                .build();
        bookingRepository.save(booking);
        return BookingResponseDTO.builder()
                .driverId(driver.getDriverId())
                .driverName(driver.getUser().getName())
                .driverContact(driver.getUser().getMobileNumber())
                .driverTruckNumber(driver.getTruckNumber())
                .fromCity(booking.getFromCity())
                .toCity(booking.getToCity())
                .bookingDate(bookingRequestDTO.getBookingDate())
                .bookedOn(booking.getBookedOn())
                .build();
    }

    public LoginDealerResponseDTO loginViaOtp(String username, Integer otp) {
        if(!otpService.isOtpValid(username, otp)) throw new InvalidOtpException();
        otpService.clearOTP(username);
        val userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(username);
        val accessToken = jwtUtil.generateToken(userDetails);
        val dealer = dealerRepository.findByUser_Username(userDetails.getUsername());
        return LoginDealerResponseDTO.builder()
                .dealer(dealer)
                .accessToken(accessToken)
                .build();
    }
}
