package com.dealersautocenter.com.payment.service;

import com.dealersautocenter.com.payment.dto.PaymentRequestDTO;
import com.dealersautocenter.com.payment.dto.PaymentResponseDTO;
import com.dealersautocenter.com.payment.entity.Dealer;
import com.dealersautocenter.com.payment.entity.Payment;
import com.dealersautocenter.com.payment.entity.PaymentStatus;
import com.dealersautocenter.com.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    private final ScheduledExecutorService scheduler= Executors.newScheduledThreadPool(1);

    private final RestTemplate restTemplate;

//    @Transactional
    public PaymentResponseDTO  initiatePayment(PaymentRequestDTO paymentRequestDTO){
        String url = "http://localhost:8080/dealers/getById/" + paymentRequestDTO.getDealerId();
        try {
            ResponseEntity<Dealer> response = restTemplate.getForEntity(url, Dealer.class);

            if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
                throw new RuntimeException("Dealer not found with ID: " + paymentRequestDTO.getDealerId());
            }
        } catch (Exception e) {
            throw new RuntimeException("Dealer validation failed: " + e.getMessage());
        }

        Payment payment =Payment.builder()
                        .dealerId(paymentRequestDTO.getDealerId())
                                .amount(paymentRequestDTO.getAmount())
                                        .paymentMethod(paymentRequestDTO.getPaymentMethod())
                                                .paymentStatus(PaymentStatus.PENDING)
                                                        .build();
        Payment saved=paymentRepository.save(payment);
        scheduler.schedule(() -> {
            saved.setPaymentStatus(PaymentStatus.SUCCCESS);
            paymentRepository.save(saved);
        }, 5, TimeUnit.SECONDS);

        return PaymentResponseDTO.builder()
                .paymentId(saved.getPaymentId())
                .dealerId(paymentRequestDTO.getDealerId())
                .amount(saved.getAmount())
                .paymentMethod(saved.getPaymentMethod())
                .paymentStatus(PaymentStatus.SUCCCESS)
                .localDateTime(saved.getDateTime())
                .build();

    }
}
