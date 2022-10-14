package com.ramy.vehicle.Vehicle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    @Query("SELECT s FROM Vehicle s WHERE s.registrationNumber = ?1")
    Optional<Vehicle> findRegistrationNumber(String registrationNumber);
}
