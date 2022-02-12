package com.devtyagi.maalgaadi.repository;

import com.devtyagi.maalgaadi.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    Boolean existsUserByUsername(String username);

}
