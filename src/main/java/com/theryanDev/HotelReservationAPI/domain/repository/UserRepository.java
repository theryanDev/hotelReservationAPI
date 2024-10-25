package com.theryanDev.HotelReservationAPI.domain.repository;

import com.theryanDev.HotelReservationAPI.domain.model.User;
import com.theryanDev.HotelReservationAPI.service.DTOuser.ListagemUserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    Page<ListagemUserDTO> findAllByAtivoTrue(Pageable pageable);
    List<ListagemUserDTO> findByName(String name);
    boolean existsByName(String name);
}
