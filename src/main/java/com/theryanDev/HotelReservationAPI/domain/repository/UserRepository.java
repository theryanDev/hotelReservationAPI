package com.theryanDev.HotelReservationAPI.domain.repository;

import com.theryanDev.HotelReservationAPI.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
