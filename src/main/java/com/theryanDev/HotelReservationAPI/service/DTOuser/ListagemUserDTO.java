package com.theryanDev.HotelReservationAPI.service.DTOuser;

import com.theryanDev.HotelReservationAPI.domain.model.User;

public record ListagemUserDTO(
        Long id,
        String name,
        String email,
        String telephone
) {
    public ListagemUserDTO (User user) {
        this(user.getId(), user.getName(), user.getEmail(), user.getTelephone());
    }
}
