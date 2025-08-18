package com.vehicle_management.dealer_vehicle_management.controller;

import com.vehicle_management.dealer_vehicle_management.dto.DealerRequestDTO;
import com.vehicle_management.dealer_vehicle_management.dto.DealerResponseDTO;
import com.vehicle_management.dealer_vehicle_management.entity.Dealer;
import com.vehicle_management.dealer_vehicle_management.service.DealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dealers")
public class DealerController {
    @Autowired
    DealerService dealerService;
    @GetMapping("/getAll")
    public ResponseEntity<List<Dealer>> getAllDealers(){

        return  ResponseEntity.ok(dealerService.getAllDealers());

    }

    @GetMapping("/getById/{dealerId}")
    public ResponseEntity<Dealer> getDealersById(@PathVariable Long dealerId){
       return ResponseEntity.ok(dealerService.findDealerById(dealerId));
    }


    @DeleteMapping("/delete/{dealerId}")
    public ResponseEntity<Dealer> deleteDealer(@PathVariable Long dealerId ){
        return ResponseEntity.ok(dealerService.deleteDealer(dealerId));
    }


    @PostMapping("/create")
    public ResponseEntity<DealerResponseDTO> createDealer(@RequestBody DealerRequestDTO dealerRequestDTO){
        return ResponseEntity.ok(dealerService.registerDealer(dealerRequestDTO));

    }
    @PatchMapping("/update/{dealerId}")
    public ResponseEntity<DealerResponseDTO> update(@PathVariable Long dealerId , @RequestBody DealerRequestDTO dealerRequestDTO){
        return ResponseEntity.ok(dealerService.updateDealer(dealerId,dealerRequestDTO));
    }





}
