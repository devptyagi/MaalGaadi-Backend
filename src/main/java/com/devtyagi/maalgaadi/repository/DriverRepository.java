package com.devtyagi.maalgaadi.repository;

import com.devtyagi.maalgaadi.dao.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, String> {

    Driver findByUser_Username(String username);

}
