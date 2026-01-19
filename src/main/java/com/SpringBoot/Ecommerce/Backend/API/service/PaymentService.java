package com.SpringBoot.Ecommerce.Backend.API.service;

import com.SpringBoot.Ecommerce.Backend.API.model.Order;
import com.SpringBoot.Ecommerce.Backend.API.model.Payment;
import com.SpringBoot.Ecommerce.Backend.API.repository.OrderRepository;
import com.SpringBoot.Ecommerce.Backend.API.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderRepository orderRepository;

    public Payment createPayment(String orderId, Double amount) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        Payment payment = new Payment();
        payment.setId(UUID.randomUUID().toString());
        payment.setOrderId(orderId);
        payment.setAmount(amount);
        payment.setStatus("PENDING");
        payment.setPaymentId("pay_mock_" + UUID.randomUUID().toString());
        payment.setCreatedAt(Instant.now());

        return paymentRepository.save(payment);
    }
}
