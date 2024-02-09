package com.belrose.cqrs.query.api.handler;

import com.belrose.cqrs.command.api.model.Product;
import com.belrose.cqrs.query.api.query.GetAllProductQuery;
import com.belrose.cqrs.query.api.query.GetProductByIdQuery;
import com.belrose.cqrs.query.api.repository.ProductRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Component
public class ProductQueryHandler {

    private final ProductRepository productRepository;

    public ProductQueryHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @QueryHandler
    public Flux<Product> getAllProduct(GetAllProductQuery query){
        return productRepository.findAll();
    }

    @QueryHandler
    public Mono<Product> getProductById(GetProductByIdQuery query){
        return productRepository.findById(query.getId())
                .switchIfEmpty(Mono.empty());
    }
}
