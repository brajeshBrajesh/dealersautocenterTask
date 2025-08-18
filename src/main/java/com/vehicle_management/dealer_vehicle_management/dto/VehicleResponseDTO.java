package com.vehicle_management.dealer_vehicle_management.dto;

import com.vehicle_management.dealer_vehicle_management.entity.Dealer;
import com.vehicle_management.dealer_vehicle_management.entity.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VehicleResponseDTO {

    private String message;
    private Long vehicleId;
    private String model;
    private Double price;
    private Vehicle.VehicleStatus status;
//    private Dealer dealer;


}
