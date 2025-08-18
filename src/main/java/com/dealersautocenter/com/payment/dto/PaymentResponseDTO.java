package com.dealersautocenter.com.payment.dto;

import com.dealersautocenter.com.payment.entity.PaymentMethod;
import com.dealersautocenter.com.payment.entity.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
public class PaymentResponseDTO {
    private Long paymentId;
    private Long dealerId;
    private Double amount;
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;

    private LocalDateTime localDateTime;

}
