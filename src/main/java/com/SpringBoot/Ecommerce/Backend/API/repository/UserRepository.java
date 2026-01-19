package com.SpringBoot.Ecommerce.Backend.API.repository;

import com.SpringBoot.Ecommerce.Backend.API.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
}
