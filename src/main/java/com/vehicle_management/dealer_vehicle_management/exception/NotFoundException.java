package com.vehicle_management.dealer_vehicle_management.exception;



public class NotFoundException extends RuntimeException {
    public NotFoundException(String resource, Object id) {
        super(resource + " with id " + id + " not found");
    }
}
