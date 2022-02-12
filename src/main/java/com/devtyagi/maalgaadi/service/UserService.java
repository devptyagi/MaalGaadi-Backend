package com.devtyagi.maalgaadi.service;

import com.devtyagi.maalgaadi.dao.User;
import com.devtyagi.maalgaadi.exception.UserNotFoundException;
import com.devtyagi.maalgaadi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUserByUsername(String username) {
        return userRepository.findById(username)
                .orElseThrow(UserNotFoundException::new);
    }

    public boolean exists(String username) {
        return userRepository.existsUserByUsername(username);
    }

}
