package com.theryanDev.HotelReservationAPI.infra.Exceptions;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
