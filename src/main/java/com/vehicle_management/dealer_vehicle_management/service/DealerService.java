package com.vehicle_management.dealer_vehicle_management.service;

import com.vehicle_management.dealer_vehicle_management.dto.DealerRequestDTO;
import com.vehicle_management.dealer_vehicle_management.dto.DealerResponseDTO;
import com.vehicle_management.dealer_vehicle_management.entity.Dealer;
import com.vehicle_management.dealer_vehicle_management.exception.DealerAlreadyExist;
import com.vehicle_management.dealer_vehicle_management.repository.DealerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DealerService {
    @Autowired
    DealerRepository dealerRepository;
    public DealerResponseDTO registerDealer(DealerRequestDTO dealerRequestDTO){

        String email=dealerRequestDTO.getEmail();
        Optional<Dealer> existing=dealerRepository.findByEmail(email);

        if (existing.isPresent()){
            throw new DealerAlreadyExist("This email is already registered!!");
        }


        Dealer dealer = Dealer.builder()
                .name(dealerRequestDTO.getName())
                .email(dealerRequestDTO.getEmail())
                .subsType(dealerRequestDTO.getSubsType())
                .build();

        dealerRepository.save(dealer);

        return  DealerResponseDTO.builder()
                .name(dealerRequestDTO.getName())
                .email(dealerRequestDTO.getEmail())
                .subsType(dealerRequestDTO.getSubsType())
                .message("Saved successfully")
                .build();

    }


    public  Dealer findDealerById(Long dealerId){
        Optional<Dealer> dealer=dealerRepository.findById(dealerId);
        if(dealer.isEmpty()){
            throw new RuntimeException("Dealer doesn't exist with ID : "+ dealerId);
        }

        return dealer.get();

    }

    public List<Dealer> getAllDealers(){
        return dealerRepository.findAll();
    }
    public Dealer deleteDealer(Long dealerId){
        Dealer dealer = dealerRepository.findById(dealerId)
                .orElseThrow(() -> new RuntimeException("Dealer not found"));
        dealerRepository.delete(dealer);
        return dealer;
    }

    public DealerResponseDTO updateDealer(Long dealerId, DealerRequestDTO dealerRequestDTO) {
        Dealer dealer = dealerRepository.findById(dealerId)
                .orElseThrow(() -> new RuntimeException("Dealer not found with ID: " + dealerId));

        // Update only non-null fields
        if (dealerRequestDTO.getName() != null && !dealerRequestDTO.getName().isBlank()) {
            dealer.setName(dealerRequestDTO.getName());
        }
        if (dealerRequestDTO.getEmail() != null && !dealerRequestDTO.getEmail().isBlank()) {

            if (dealerRepository.existsByEmail(dealerRequestDTO.getEmail()) && !dealerRequestDTO.getEmail().equals(dealer.getEmail())) {
                throw new RuntimeException("Dealer already exists with email: " + dealerRequestDTO.getEmail());
            }
            dealer.setEmail(dealerRequestDTO.getEmail());
        }
        if (dealerRequestDTO.getSubsType() != null) {
            dealer.setSubsType(dealerRequestDTO.getSubsType());
        }

        // save updated dealer
        dealerRepository.save(dealer);

        return DealerResponseDTO.builder()
                .message("Dealer updated successfully")
                .name(dealer.getName())
                .email(dealer.getEmail())
                .subsType(dealer.getSubsType())
                .build();
    }
}
