package com.ramy.vehicle.Vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
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
            throw new IllegalStateException("nomor registrasi sudah terdaftar");
        }

        // if not, save into database
        return vehicleRepository.save(vehicle);
    }

    public List<Vehicle> searchVehicle(String registrationNumber, String ownerName) {
        List<Vehicle> vehicles = null;

        if (registrationNumber.equals("") && StringUtils.hasText(ownerName)) {
            vehicles = vehicleRepository.searchOwnerName(ownerName);
        } else if (StringUtils.hasText(registrationNumber) && ownerName.equals("")) {
            vehicles = vehicleRepository.searchRegistrationNumber(registrationNumber);
        } else {
            vehicles = vehicleRepository.searchOwnerNameOrRegistrationNumber(ownerName, registrationNumber);
        }

        return vehicles;
    }
}
