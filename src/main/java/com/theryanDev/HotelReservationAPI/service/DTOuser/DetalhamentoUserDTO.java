package com.theryanDev.HotelReservationAPI.service.DTOuser;

import com.theryanDev.HotelReservationAPI.domain.model.User;

public record DetalhamentoUserDTO(
        Long id,
        String name,
        String email,
        String telephone,
        Boolean ativo
) {
    public DetalhamentoUserDTO (User user) {
        this(user.getId(), user.getName(), user.getEmail(), user.getTelephone(), user.getAtivo());
    }
}