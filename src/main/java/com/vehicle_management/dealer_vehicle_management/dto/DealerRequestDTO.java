package com.vehicle_management.dealer_vehicle_management.dto;

import com.vehicle_management.dealer_vehicle_management.entity.Dealer;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DealerRequestDTO {
    private String email;
    private String name;
    private Dealer.SubsType subsType;

}
