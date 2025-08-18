package com.vehicle_management.dealer_vehicle_management.dto;

import com.vehicle_management.dealer_vehicle_management.entity.Dealer;
import com.vehicle_management.dealer_vehicle_management.entity.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class DealerResponseDTO {
    private String message;
    private String email;
    private String name;
    private Dealer.SubsType subsType;
//    private List<Vehicle> vehicles;
}
