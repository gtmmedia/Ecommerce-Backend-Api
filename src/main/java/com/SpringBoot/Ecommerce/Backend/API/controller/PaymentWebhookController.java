package com.SpringBoot.Ecommerce.Backend.API.controller;

import com.SpringBoot.Ecommerce.Backend.API.dto.PaymentWebhookRequest;
import com.SpringBoot.Ecommerce.Backend.API.model.Order;
import com.SpringBoot.Ecommerce.Backend.API.model.Payment;
import com.SpringBoot.Ecommerce.Backend.API.repository.OrderRepository;
import com.SpringBoot.Ecommerce.Backend.API.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/webhooks")
public class PaymentWebhookController {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping("/payment")
    public ResponseEntity<String> handlePaymentWebhook(@RequestBody PaymentWebhookRequest request) {
        if ("payment.captured".equals(request.getEvent())) {
            Map<String, Object> payload = request.getPayload();
            if (payload != null && payload.containsKey("payment")) {
                Map<String, Object> paymentData = (Map<String, Object>) payload.get("payment");
                String razorpayPaymentId = (String) paymentData.get("id");

                Payment payment = paymentRepository.findByPaymentId(razorpayPaymentId);
                if (payment != null) {
                    payment.setStatus("SUCCESS");
                    paymentRepository.save(payment);

                    Order order = orderRepository.findById(payment.getOrderId()).orElse(null);
                    if (order != null) {
                        order.setStatus("PAID");
                        orderRepository.save(order);
                    }
                }
            }
        }
        return ResponseEntity.ok("Received");
    }
}
