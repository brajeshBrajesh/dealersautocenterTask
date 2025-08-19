package com.vehicle_management.dealer_vehicle_management.controller;

import com.vehicle_management.dealer_vehicle_management.dto.DealerRequestDTO;
import com.vehicle_management.dealer_vehicle_management.dto.DealerResponseDTO;
import com.vehicle_management.dealer_vehicle_management.dto.VehicleRequestDTO;
import com.vehicle_management.dealer_vehicle_management.dto.VehicleResponseDTO;
import com.vehicle_management.dealer_vehicle_management.entity.Dealer;
import com.vehicle_management.dealer_vehicle_management.entity.Vehicle;
import com.vehicle_management.dealer_vehicle_management.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {
    @Autowired
    VehicleService vehicleService;
    @GetMapping("/getAll")
    public  ResponseEntity<List<Vehicle>> getAllVehicles(){
        return ResponseEntity.ok(vehicleService.getAllVehicles());
    }

    @GetMapping("/getById")
    public ResponseEntity<Vehicle> getVehiclesById(@RequestParam Long vehicleId){
        return ResponseEntity.ok(vehicleService.findVehicleById(vehicleId));
    }


    @DeleteMapping("/delete/{vehicleId}")
    public ResponseEntity<Vehicle> deleteVehicle(@PathVariable Long vehicleId ){
        return ResponseEntity.ok(vehicleService.deleteVehicle(vehicleId));
    }


    @PostMapping("/create")
    public ResponseEntity<VehicleResponseDTO> createVehicle(@RequestBody VehicleRequestDTO vehicleRequestDTO){
        return ResponseEntity.ok(vehicleService.registerVehicle(vehicleRequestDTO));

    }
    @PatchMapping("/update/{vehicleId}")
    public ResponseEntity<VehicleResponseDTO> update(@PathVariable Long vehicleId , @RequestBody VehicleRequestDTO vehicleRequestDTO){
        return ResponseEntity.ok(vehicleService.updateVehicle(vehicleId,vehicleRequestDTO));
    }
    @GetMapping("/premium")
    public ResponseEntity<List<Vehicle>> getPremiumDealerVehicles() {
        List<Vehicle> vehicles = vehicleService.getVehiclesOfPremiumDealers();
        return ResponseEntity.ok(vehicles);
    }

}
