package com.devtyagi.maalgaadi.service;

import com.devtyagi.maalgaadi.model.CustomUserDetails;
import com.devtyagi.maalgaadi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        val user = userRepository.findById(username).orElse(null);
        if(user == null) {
            throw new UsernameNotFoundException("No user exists with the given email!");
        }
        return new CustomUserDetails(user);
    }
}
