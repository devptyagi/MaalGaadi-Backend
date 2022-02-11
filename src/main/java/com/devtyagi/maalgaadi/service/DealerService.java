package com.devtyagi.maalgaadi.service;

import com.devtyagi.maalgaadi.dao.Dealer;
import com.devtyagi.maalgaadi.dao.User;
import com.devtyagi.maalgaadi.dto.request.LoginRequestDTO;
import com.devtyagi.maalgaadi.dto.request.SignupDealerRequestDTO;
import com.devtyagi.maalgaadi.dto.response.LoginDealerResponseDTO;
import com.devtyagi.maalgaadi.enums.UserRole;
import com.devtyagi.maalgaadi.exception.InvalidCredentialsException;
import com.devtyagi.maalgaadi.model.CustomUserDetails;
import com.devtyagi.maalgaadi.repository.DealerRepository;
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
public class DealerService {

    private final BCryptPasswordEncoder passwordEncoder;

    private final DealerRepository dealerRepository;

    private final AuthenticationManager authenticationManager;

    private final JwtUserDetailsService userDetailsService;

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

}
