package com.theryanDev.HotelReservationAPI.service.DTOhotel;

import com.theryanDev.HotelReservationAPI.domain.endereco.Location;
import com.theryanDev.HotelReservationAPI.domain.model.Hotel;
import com.theryanDev.HotelReservationAPI.service.DTOroom.DadosBasicRoom;

import java.util.List;

public record DetalhamentoHotelDTO(Long id, String name, Location location, List<DadosBasicRoom> rooms) {
    public DetalhamentoHotelDTO (Hotel hotel) {
        this(hotel.getId(), hotel.getName(), hotel.getLocation(), hotel.getRooms().stream()
                .map(r -> new DadosBasicRoom(r.getType(), r.getPrice(), r.getIsAvailable()))
                .toList());
    }
}
