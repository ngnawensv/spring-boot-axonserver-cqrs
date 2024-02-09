package com.belrose.cqrs.query.api.repository;


import com.belrose.cqrs.command.api.model.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProductRepository extends ReactiveMongoRepository<Product,String> {
}
