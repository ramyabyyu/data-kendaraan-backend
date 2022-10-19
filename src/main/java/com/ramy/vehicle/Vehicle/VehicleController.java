package com.ramy.vehicle.Vehicle;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
        return vehicleRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    @PostMapping("/vehicle")
    @CrossOrigin(origins = "*")
    Vehicle save(@RequestBody Vehicle newVehicle) {
        if (newVehicle.getRegistrationNumber().equals("")) {
            throw new IllegalStateException("nomor registrasi harus diisi!");
        }

        if (newVehicle.getOwnerName().equals("")) {
            throw new IllegalStateException("nama pemilik harus diisi!");
        }

        if (newVehicle.getVehicleBrand().equals("")) {
            throw new IllegalStateException("merk kendaraan harus diisi!");
        }

        if (newVehicle.getAddress().equals("")) {
            throw new IllegalStateException("alamat pemilik harus diisi!");
        }

        if (newVehicle.getProductionYear() == 0) {
            throw new IllegalStateException("tahun pembuatan harus diisi!");
        }

        if (newVehicle.getCylinderCapacity() == 0) {
            throw new IllegalStateException("kapasitas silinder harus diisi!");
        }

        if (newVehicle.getVehicleColor().equals("")) {
            throw new IllegalStateException("warna kendaraan harus diisi!");
        }

        if (newVehicle.getFuel().equals("")) {
            throw new IllegalStateException("jenis bahan bakar harus diisi!");
        }

        return vehicleService.saveOrFail(newVehicle);
    }

    @PostMapping("/search")
    @CrossOrigin(origins = "*")
    List<Vehicle> search(@RequestBody Vehicle searchVehicle) {
        return vehicleService.searchVehicle(searchVehicle.getRegistrationNumber(), searchVehicle.getOwnerName());
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

//        if (newVehicle.getOwnerName().equals("")) {
//            throw new IllegalStateException("nama pemilik harus diisi!");
//        }
//
//        if (newVehicle.getAddress().equals("")) {
//            throw new IllegalStateException("alamat pemilik harus diisi!");
//        }
//
//        if (newVehicle.getFuel().equals("")) {
//            throw new IllegalStateException("jenis bahan bakar harus diisi!");
//        }
//
//        if (newVehicle.getVehicleBrand().equals("")) {
//            throw new IllegalStateException("merk kendaraan harus diisi!");
//        }
//
//        if (newVehicle.getVehicleColor().equals("")) {
//            throw new IllegalStateException("warna kendaraan harus diisi!");
//        }
//
//        if (newVehicle.getVehicleColor().equals("")) {
//            throw new IllegalStateException("warna kendaraan harus diisi!");
//        }
//
//        if (newVehicle.getVehicleColor().equals("")) {
//            throw new IllegalStateException("warna kendaraan harus diisi!");
//        }

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
