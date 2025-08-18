package com.dealersautocenter.com.payment.controller;

import com.dealersautocenter.com.payment.dto.PaymentRequestDTO;
import com.dealersautocenter.com.payment.dto.PaymentResponseDTO;
import com.dealersautocenter.com.payment.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
    @PostMapping("/initiate")
    public ResponseEntity<PaymentResponseDTO> initiatePayment(@Valid @RequestBody PaymentRequestDTO paymentRequestDto){
        return ResponseEntity.ok(paymentService.initiatePayment(paymentRequestDto));
    }
}
