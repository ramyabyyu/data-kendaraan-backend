package com.ramy.vehicle.Vehicle;

import javax.persistence.*;

@Entity
@Table(name = "vehicles")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "registration_number", unique = true, nullable = false)
    private String registrationNumber;

    @Column(name = "owner_name", nullable = false)
    private String ownerName;

    @Column(name = "address", length = 1000)
    private String address;

    @Column(name = "vehicle_brand")
    private String vehicleBrand;

    @Column(name = "production_year", length = 4)
    private Integer productionYear;

    @Column(name = "cylinder_capacity")
    private Integer cylinderCapacity;

    @Column(name = "vehicle_color")
    private String vehicleColor;

    @Column(name = "fuel")
    private String fuel;

    public Vehicle() {
    }

    public Vehicle(String registrationNumber,
                   String ownerName,
                   String address,
                   String vehicleBrand,
                   Integer productionYear,
                   Integer cylinderCapacity,
                   String vehicleColor,
                   String fuel) {
        this.registrationNumber = registrationNumber;
        this.ownerName = ownerName;
        this.address = address;
        this.vehicleBrand = vehicleBrand;
        this.productionYear = productionYear;
        this.cylinderCapacity = cylinderCapacity;
        this.vehicleColor = vehicleColor;
        this.fuel = fuel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getVehicleBrand() {
        return vehicleBrand;
    }

    public void setVehicleBrand(String vehicleBrand) {
        this.vehicleBrand = vehicleBrand;
    }

    public Integer getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(Integer productionYear) {
        this.productionYear = productionYear;
    }

    public Integer getCylinderCapacity() {
        return cylinderCapacity;
    }

    public void setCylinderCapacity(Integer cylinderCapacity) {
        this.cylinderCapacity = cylinderCapacity;
    }

    public String getVehicleColor() {
        return vehicleColor;
    }

    public void setVehicleColor(String vehicleColor) {
        this.vehicleColor = vehicleColor;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }
}
