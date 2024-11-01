package com.theryanDev.HotelReservationAPI.service.DTOhotel;

import com.theryanDev.HotelReservationAPI.domain.endereco.Location;
import com.theryanDev.HotelReservationAPI.domain.model.Hotel;
import com.theryanDev.HotelReservationAPI.domain.model.Room;

import java.util.List;

public record ListagemHotelsDTO(String nome, Location location, List<Room> rooms) {
    public ListagemHotelsDTO (Hotel hotel) {
        this(hotel.getName(), hotel.getLocation(), hotel.getRooms());
    }
}
