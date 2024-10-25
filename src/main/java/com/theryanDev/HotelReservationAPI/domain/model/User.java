package com.theryanDev.HotelReservationAPI.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String telephone;
    private String password;
    private Boolean ativo;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    public User(String name, String email, String telephone,String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.telephone = telephone;
        this.ativo = true;
    }
}
