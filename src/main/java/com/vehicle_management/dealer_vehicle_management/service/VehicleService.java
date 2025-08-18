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
//    public DealerResponseDTO updateDealer(Long dealerId, DealerRequestDTO dealerRequestDTO) {
//        Dealer dealer = dealerRepository.findById(dealerId)
//                .orElseThrow(() -> new RuntimeException("Dealer not found with ID: " + dealerId));
//
//        // Update only non-null fields
//        if (dealerRequestDTO.getName() != null && !dealerRequestDTO.getName().isBlank()) {
//            dealer.setName(dealerRequestDTO.getName());
//        }
//        if (dealerRequestDTO.getEmail() != null && !dealerRequestDTO.getEmail().isBlank()) {
//
//            if (dealerRepository.existsByEmail(dealerRequestDTO.getEmail()) && !dealerRequestDTO.getEmail().equals(dealer.getEmail())) {
//                throw new RuntimeException("Dealer already exists with email: " + dealerRequestDTO.getEmail());
//            }
//            dealer.setEmail(dealerRequestDTO.getEmail());
//        }
//        if (dealerRequestDTO.getSubsType() != null) {
//            dealer.setSubsType(dealerRequestDTO.getSubsType());
//        }
//
//        // save updated dealer
//        dealerRepository.save(dealer);
//
//        return DealerResponseDTO.builder()
//                .message("Dealer updated successfully")
//                .name(dealer.getName())
//                .email(dealer.getEmail())
//                .subsType(dealer.getSubsType())
//                .build();
//    }
}
