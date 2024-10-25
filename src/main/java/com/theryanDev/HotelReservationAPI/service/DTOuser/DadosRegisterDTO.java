package com.theryanDev.HotelReservationAPI.service.DTOuser;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosRegisterDTO(
        @NotBlank(message = "Name is required")
        String name,
        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email")
        String email,
        @NotBlank(message = "Telephone is required")
        String telephone,
        @NotBlank(message = "Password is required")
        String password
) { }

