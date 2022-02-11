package com.devtyagi.maalgaadi.repository;

import com.devtyagi.maalgaadi.dao.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, String> {
}
