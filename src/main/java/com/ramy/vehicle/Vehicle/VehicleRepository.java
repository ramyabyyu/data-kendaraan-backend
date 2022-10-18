package com.ramy.vehicle.Vehicle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    @Query("SELECT v FROM Vehicle v WHERE v.registrationNumber = ?1")
    Optional<Vehicle> findRegistrationNumber(String registrationNumber);

    @Query("SELECT v FROM Vehicle v WHERE v.registrationNumber LIKE %?1%")
    List<Vehicle> searchRegistrationNumber(String registrationNumber);

    @Query("SELECT v FROM Vehicle v WHERE v.ownerName LIKE %?1%")
    List<Vehicle> searchOwnerName(String ownerName);

    @Query("SELECT v FROM Vehicle v WHERE v.ownerName LIKE %?1% AND v.registrationNumber LIKE %?2%")
    List<Vehicle> searchOwnerNameOrRegistrationNumber(String ownerName, String registrationNumber);
}
