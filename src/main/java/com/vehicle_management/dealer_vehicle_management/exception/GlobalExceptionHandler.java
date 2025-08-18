


package com.vehicle_management.dealer_vehicle_management.exception;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

//@RestControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(NotFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public ApiError handleNotFound(NotFoundException ex, HttpServletRequest req) {
//        return ApiError.builder()
//                .timestamp(OffsetDateTime.now())
//                .status(HttpStatus.NOT_FOUND.value())
//                .error(HttpStatus.NOT_FOUND.getReasonPhrase())
//                .message(ex.getMessage())
//                .path(req.getRequestURI())
//                .build();
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ApiError handleValidation(MethodArgumentNotValidException ex, HttpServletRequest req) {
//        Map<String, String> errors = new LinkedHashMap<>();
//        for (FieldError fe : ex.getBindingResult().getFieldErrors()) {
//            errors.put(fe.getField(), fe.getDefaultMessage());
//        }
//        return ApiError.builder()
//                .timestamp(OffsetDateTime.now())
//                .status(HttpStatus.BAD_REQUEST.value())
//                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
//                .message("Validation failed")
//                .path(req.getRequestURI())
//                .validation(errors)
//                .build();
//    }
//
//    @ExceptionHandler(DataIntegrityViolationException.class)
//    @ResponseStatus(HttpStatus.CONFLICT)
//    public ApiError handleConstraint(DataIntegrityViolationException ex, HttpServletRequest req) {
//        return ApiError.builder()
//                .timestamp(OffsetDateTime.now())
//                .status(HttpStatus.CONFLICT.value())
//                .error(HttpStatus.CONFLICT.getReasonPhrase())
//                .message("Data integrity violation: " + ex.getMostSpecificCause().getMessage())
//                .path(req.getRequestURI())
//                .build();
//    }
//
//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ApiError handleBadJson(HttpMessageNotReadableException ex, HttpServletRequest req) {
//        return ApiError.builder()
//                .timestamp(OffsetDateTime.now())
//                .status(HttpStatus.BAD_REQUEST.value())
//                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
//                .message("Malformed JSON or enum value: " + ex.getMostSpecificCause().getMessage())
//                .path(req.getRequestURI())
//                .build();
//    }

    @ExceptionHandler(DealerAlreadyExist.class)
    public ResponseEntity<String>  dealerAlreadyExist(Exception ex,HttpServletRequest req){
        return new ResponseEntity<>("Dublicate user " + ex.getMessage(),
                HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleOther(Exception ex, HttpServletRequest req) {
                return new ResponseEntity<>("Global handler caught: " + ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

