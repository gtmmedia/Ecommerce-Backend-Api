package com.SpringBoot.Ecommerce.Backend.API.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.Instant;
import java.util.List;

@Document(collection = "orders")
@Data
public class Order {
    @Id
    private String id;

    private String userId;
    private Double totalAmount;
    private String status;
    private Instant createdAt;

    private List<OrderItem> items;

    @Transient
    private Payment payment;
}
