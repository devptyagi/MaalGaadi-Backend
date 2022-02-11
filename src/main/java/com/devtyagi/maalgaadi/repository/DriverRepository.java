package com.devtyagi.maalgaadi.repository;

import com.devtyagi.maalgaadi.dao.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DriverRepository extends JpaRepository<Driver, String> {

    Driver findByUser_Username(String username);

    Page<Driver> findAllByInterestedRoutes_FromCityOrInterestedRoutes_ToCity(String fromCity, String toCity, Pageable pageable);

    default Page<Driver> getAllDriversByCity(String city, Pageable pageable) {
        return findAllByInterestedRoutes_FromCityOrInterestedRoutes_ToCity(city, city, pageable);
    }
}
