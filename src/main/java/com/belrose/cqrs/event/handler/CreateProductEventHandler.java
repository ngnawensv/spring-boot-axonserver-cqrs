package com.belrose.cqrs.event.handler;

import com.belrose.cqrs.event.CreateProductEvent;
import com.belrose.cqrs.command.api.model.Product;
import com.belrose.cqrs.query.api.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CreateProductEventHandler {

    private final ProductRepository productRepository;

    public CreateProductEventHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @EventHandler
    public void createProduct(CreateProductEvent event){
        log.info("CreateProductEventHandler:createProduct:event {}",event);
        //Build product from event
        Product product = Product.builder()
                .id(event.getId())
                .sku(event.getSku())
                .name(event.getName())
                .price(event.getPrice())
                .quantity(event.getQuantity())
                .build();

        //send product to save in database
        productRepository.save(product).subscribe();

    }
}
