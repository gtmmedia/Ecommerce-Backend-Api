package com.SpringBoot.Ecommerce.Backend.API.service;

import com.SpringBoot.Ecommerce.Backend.API.model.CartItem;
import com.SpringBoot.Ecommerce.Backend.API.model.Order;
import com.SpringBoot.Ecommerce.Backend.API.model.OrderItem;
import com.SpringBoot.Ecommerce.Backend.API.model.Payment;
import com.SpringBoot.Ecommerce.Backend.API.repository.CartRepository;
import com.SpringBoot.Ecommerce.Backend.API.repository.OrderRepository;
import com.SpringBoot.Ecommerce.Backend.API.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    public Order createOrder(String userId) {
        List<CartItem> cartItems = cartRepository.findByUserId(userId);
        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        Order order = new Order();
        order.setId(UUID.randomUUID().toString());
        order.setUserId(userId);
        order.setStatus("CREATED");
        order.setCreatedAt(Instant.now());

        double totalAmount = 0;
        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setId(UUID.randomUUID().toString());
            // orderItem.setOrder(order); // Embedded
            orderItem.setProductId(cartItem.getProduct().getId());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getProduct().getPrice());

            orderItems.add(orderItem);
            totalAmount += cartItem.getProduct().getPrice() * cartItem.getQuantity();
        }

        order.setItems(orderItems);
        order.setTotalAmount(totalAmount);

        Order savedOrder = orderRepository.save(order);

        // Clear cart
        cartRepository.deleteAll(cartItems);

        return savedOrder;
    }

    public Order getOrder(String orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        Payment payment = paymentRepository.findByOrderId(orderId);
        if (payment != null) {
            order.setPayment(payment);
        }
        return order;
    }
}
