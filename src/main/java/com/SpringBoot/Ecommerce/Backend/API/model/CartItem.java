package com.SpringBoot.Ecommerce.Backend.API.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cart_items")
@Data
public class CartItem {
    @Id
    private String id;

    private String userId;

    @DBRef
    private Product product;

    private Integer quantity;
}
