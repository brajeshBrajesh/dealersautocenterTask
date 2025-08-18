package com.vehicle_management.dealer_vehicle_management.repository;

import com.vehicle_management.dealer_vehicle_management.entity.Dealer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DealerRepository extends JpaRepository<Dealer,Long> {
 Optional<Dealer> findByEmail(String email);
 Optional<Dealer> findById(Long id);
 boolean existsByEmail(String email);
}
