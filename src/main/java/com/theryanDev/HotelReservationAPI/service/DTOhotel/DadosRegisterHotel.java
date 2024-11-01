package com.theryanDev.HotelReservationAPI.service.DTOhotel;

import com.theryanDev.HotelReservationAPI.domain.endereco.DadosLocation;
import com.theryanDev.HotelReservationAPI.domain.endereco.Location;
import com.theryanDev.HotelReservationAPI.domain.model.Room;
import com.theryanDev.HotelReservationAPI.service.DTOroom.DadosBasicRoom;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DadosRegisterHotel(
        @NotBlank(message = "Name is required")
        String name,
        @NotNull(message = "Location is required")
        @Valid
        DadosLocation location,
        @NotNull(message = "Rooms are required")
        @Valid
        List<DadosBasicRoom> rooms
) {
}
