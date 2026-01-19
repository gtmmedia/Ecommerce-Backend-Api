package com.SpringBoot.Ecommerce.Backend.API.dto;

import lombok.Data;

@Data
public class PaymentRequest {
    private String orderId;
    private Double amount;
}
