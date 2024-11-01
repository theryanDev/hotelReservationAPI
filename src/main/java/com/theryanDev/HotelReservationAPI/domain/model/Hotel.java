package com.theryanDev.HotelReservationAPI.domain.model;

import com.theryanDev.HotelReservationAPI.domain.endereco.Location;
import com.theryanDev.HotelReservationAPI.service.DTOhotel.DadosRegisterHotel;
import com.theryanDev.HotelReservationAPI.service.DTOroom.DadosBasicRoom;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "hotels")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Embedded
    private Location location;
    @OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Room> rooms;

    public Hotel(DadosRegisterHotel dados){
        this.name = dados.name();
        this.location = new Location(dados.location());
        this.setRooms(dados.rooms());
    }

    public void setRooms(List<DadosBasicRoom> dados) {
        List<Room> rooms = dados.stream()
                .map(dadosRoom -> new Room(dadosRoom.type(), dadosRoom.price(), dadosRoom.isAvailable(), this))
                .collect(Collectors.toList());
        this.rooms = rooms;
    }
}
