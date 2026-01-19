package com.SpringBoot.Ecommerce.Backend.API.controller;

import com.SpringBoot.Ecommerce.Backend.API.dto.CreateOrderRequest;
import com.SpringBoot.Ecommerce.Backend.API.model.Order;
import com.SpringBoot.Ecommerce.Backend.API.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody CreateOrderRequest request) {
        return ResponseEntity.ok(orderService.createOrder(request.getUserId()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable String id) {
        return ResponseEntity.ok(orderService.getOrder(id));
    }
}
