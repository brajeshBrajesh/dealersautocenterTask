package com.vehicle_management.dealer_vehicle_management.service;

import com.vehicle_management.dealer_vehicle_management.dto.DealerRequestDTO;
import com.vehicle_management.dealer_vehicle_management.dto.DealerResponseDTO;
import com.vehicle_management.dealer_vehicle_management.dto.VehicleRequestDTO;
import com.vehicle_management.dealer_vehicle_management.dto.VehicleResponseDTO;
import com.vehicle_management.dealer_vehicle_management.entity.Dealer;
import com.vehicle_management.dealer_vehicle_management.entity.Vehicle;
import com.vehicle_management.dealer_vehicle_management.exception.DealerAlreadyExist;
import com.vehicle_management.dealer_vehicle_management.repository.DealerRepository;
import com.vehicle_management.dealer_vehicle_management.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private DealerRepository dealerRepository;

    public VehicleResponseDTO registerVehicle(VehicleRequestDTO vehicleRequestDTO){

        Dealer dealer = dealerRepository.findById(vehicleRequestDTO.getDealerId())
                .orElseThrow(() -> new RuntimeException("Dealer not found"));
        Vehicle vehicle=Vehicle.builder()
                .model(vehicleRequestDTO.getModel())
                .price(vehicleRequestDTO.getPrice())
                .status(vehicleRequestDTO.getStatus())
                .dealer(dealer)
                .build();



        Vehicle saved=vehicleRepository.save(vehicle);

        return  VehicleResponseDTO.builder()
                .vehicleId(saved.getId())
                .model(saved.getModel())
                .price(saved.getPrice())
                .status(saved.getStatus())
                .message("Vehicle registered successfully!")
                .build();

    }

    public List<Vehicle> getAllVehicles(){
        return vehicleRepository.findAll();
    }
    public  Vehicle findVehicleById(Long vehicleId){
        Optional<Vehicle> vehicle=vehicleRepository.findById(vehicleId);
        if(vehicle.isEmpty()){
            throw new RuntimeException("Vehicle doesn't exist with ID : "+ vehicle);
        }

        return vehicle.get();

    }

    public Vehicle deleteVehicle(Long vehicleId){
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
        vehicleRepository.delete(vehicle);
        return vehicle;
    }

    public List<Vehicle> getVehiclesOfPremiumDealers() {
        return vehicleRepository.findVehiclesOfPremiumDealers();
    }
//
    public VehicleResponseDTO updateVehicle(Long vehicleId, VehicleRequestDTO vehicleRequestDTO) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found with ID: " + vehicleId));

        // Update only non-null fields price,dealerId,status
        if (vehicleRequestDTO.getModel() != null && !vehicleRequestDTO.getModel().isBlank()) {
            vehicle.setModel(vehicleRequestDTO.getModel());
        }
        if (vehicleRequestDTO.getPrice() != null) {

                vehicle.setPrice(vehicleRequestDTO.getPrice());
//            if (dealerRepository.existsByEmail(dealerRequestDTO.getEmail()) && !dealerRequestDTO.getEmail().equals(dealer.getEmail())) {
//                throw new RuntimeException("Dealer already exists with email: " + dealerRequestDTO.getEmail());
//            }
//            dealer.setEmail(dealerRequestDTO.getEmail());
        }
        if(vehicleRequestDTO.getDealerId()!=null){
//            if(!dealerRepository.existsById(vehicleRequestDTO.getDealerId())){
//                throw  new RuntimeException(("Dealer with dealerId = "+vehicleRequestDTO.getDealerId()+" does not exist!"));
//            }
            Dealer dealer = dealerRepository.findById(vehicleRequestDTO.getDealerId())
                    .orElseThrow(() -> new RuntimeException("Dealer with dealerId = "+vehicleRequestDTO.getDealerId()+" does not exist!"));

            vehicle.setDealer(dealer);
        }
        if (vehicleRequestDTO.getStatus() != null) {
            vehicle.setStatus(vehicleRequestDTO.getStatus());
        }

        // save updated dealer
        vehicleRepository.save(vehicle);


        return VehicleResponseDTO.builder()
                .message("Vehicle updated successfully")
                .vehicleId(vehicle.getId())
                .model(vehicle.getModel())
                .price(vehicle.getPrice())
                .status(vehicle.getStatus())
                .build();
    }
}
