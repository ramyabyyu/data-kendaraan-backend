package com.ramy.vehicle.Vehicle;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class VehicleController {
    private VehicleRepository vehicleRepository;

    private VehicleService vehicleService;

    public VehicleController(VehicleRepository vehicleRepository, VehicleService vehicleService) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleService = vehicleService;
    }

    @GetMapping("/vehicles")
    @CrossOrigin(origins = "*")
    List<Vehicle> all() {
        return vehicleRepository.findAll();
    }

    @PostMapping("/vehicle")
    @CrossOrigin(origins = "*")
    Vehicle save(@RequestBody Vehicle newVehicle) {
        return vehicleService.saveOrFail(newVehicle);
    }

    @GetMapping("/vehicle/{id}")
    @CrossOrigin(origins = "*")
    Vehicle get(@PathVariable Long id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new VehicleNotFoundException(id));
    }

    @PutMapping("/vehicle/{id}")
    @CrossOrigin(origins = "*")
    Vehicle update(@PathVariable Long id, @RequestBody Vehicle newVehicle) {
        return vehicleRepository.findById(id).map(vehicle -> {
            vehicle.setOwnerName(newVehicle.getOwnerName());
            vehicle.setAddress(newVehicle.getAddress());
            vehicle.setVehicleBrand(newVehicle.getVehicleBrand());
            vehicle.setProductionYear(newVehicle.getProductionYear());
            vehicle.setCylinderCapacity(newVehicle.getCylinderCapacity());
            vehicle.setVehicleColor(newVehicle.getVehicleColor());
            vehicle.setFuel(newVehicle.getFuel());
            return vehicleRepository.save(vehicle);
        }).orElseThrow(() -> new VehicleNotFoundException(id));
    }

    @DeleteMapping("/vehicle/{id}")
    @CrossOrigin(origins = "*")
    String delete(@PathVariable Long id) {
        vehicleRepository.deleteById(id);
        return "Delete Success";
    }
}
