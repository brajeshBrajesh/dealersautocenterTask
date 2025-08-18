package com.vehicle_management.dealer_vehicle_management.repository;

import com.vehicle_management.dealer_vehicle_management.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle,Long> {
    Optional<Vehicle> findById(Long id);
    @Query("SELECT v FROM Vehicle v WHERE v.dealer.subsType = 'PREMIUM'")
    List<Vehicle> findVehiclesOfPremiumDealers();
}
