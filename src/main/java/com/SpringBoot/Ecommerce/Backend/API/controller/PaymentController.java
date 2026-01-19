package com.SpringBoot.Ecommerce.Backend.API.controller;

import com.SpringBoot.Ecommerce.Backend.API.dto.PaymentRequest;
import com.SpringBoot.Ecommerce.Backend.API.model.Payment;
import com.SpringBoot.Ecommerce.Backend.API.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create")
    public ResponseEntity<Payment> createPayment(@RequestBody PaymentRequest request) {
        return ResponseEntity.ok(paymentService.createPayment(request.getOrderId(), request.getAmount()));
    }
}
