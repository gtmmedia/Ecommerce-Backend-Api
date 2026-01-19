package com.SpringBoot.Ecommerce.Backend.API.repository;

import com.SpringBoot.Ecommerce.Backend.API.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
}
