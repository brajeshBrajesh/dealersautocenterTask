package com.vehicle_management.dealer_vehicle_management.exception;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


//@RestControllerAdvice
public class GlobalExceptionHandler {



    @ExceptionHandler(DealerAlreadyExist.class)
    public ResponseEntity<String>  dealerAlreadyExist(Exception ex,HttpServletRequest req){
        return new ResponseEntity<>("Duplicate user " + ex.getMessage(),
                HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleOther(Exception ex, HttpServletRequest req) {
                return new ResponseEntity<>("Global handler caught: " + ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

