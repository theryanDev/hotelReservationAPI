package com.theryanDev.HotelReservationAPI.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Payment {
    private BigDecimal amount;
    private String status;
}
