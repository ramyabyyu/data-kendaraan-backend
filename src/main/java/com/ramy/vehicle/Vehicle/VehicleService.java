package com.ramy.vehicle.Vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }
    
    public Vehicle saveOrFail(Vehicle vehicle) {
        Optional<Vehicle> registrationNumber = vehicleRepository.findRegistrationNumber(vehicle.getRegistrationNumber());

        // if registration number already exist? throw an exception
        if (registrationNumber.isPresent()) {
            throw new IllegalStateException("registration number is already exist");
        }

        // if not, save into database
        return vehicleRepository.save(vehicle);
    }
}
