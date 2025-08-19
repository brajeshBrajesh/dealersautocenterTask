package com.vehicle_management.dealer_vehicle_management.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "vehicles")
public class Vehicle {
    public enum VehicleStatus {
        AVAILABLE, SOLD
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String model;
    private Double price;

    @Enumerated(EnumType.STRING)
    private VehicleStatus status;

    @ManyToOne
    @JoinColumn(name = "dealer_id", nullable = false)
//    @JsonBackReference
    @JsonIgnoreProperties("vehicles")
    private Dealer dealer;
}
