package com.ramy.vehicle.Vehicle;

public class VehicleNotFoundException extends RuntimeException {
    VehicleNotFoundException(Long id) {
        super(String.format("Vehicle with id = %s is not found", id));
    }
}
