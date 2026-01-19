package com.SpringBoot.Ecommerce.Backend.API.repository;

import com.SpringBoot.Ecommerce.Backend.API.model.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends MongoRepository<Payment, String> {
    Payment findByOrderId(String orderId);

    Payment findByPaymentId(String paymentId);
}
