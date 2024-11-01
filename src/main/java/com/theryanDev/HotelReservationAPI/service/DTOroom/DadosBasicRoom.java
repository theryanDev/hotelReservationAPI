package com.theryanDev.HotelReservationAPI.service.DTOroom;

import com.theryanDev.HotelReservationAPI.domain.model.Room;
import com.theryanDev.HotelReservationAPI.domain.model.RoomType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DadosBasicRoom (
        @NotNull(message = "Type is required")
        RoomType type,
        @NotNull(message = "Price is required")
        BigDecimal price,
        @NotNull(message = "Availability is required")
        Boolean isAvailable
){}
