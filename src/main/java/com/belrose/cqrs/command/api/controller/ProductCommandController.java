package com.belrose.cqrs.command.api.controller;

import com.belrose.cqrs.command.api.command.CreateProductCommand;
import com.belrose.cqrs.command.api.model.Product;
import jakarta.validation.Valid;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/products")
public class ProductCommandController {

    private final CommandGateway commandGateway;

    public ProductCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public CompletableFuture<?> createProduct(@RequestBody @Valid Product product){
        //Build command from product
        var command = CreateProductCommand.builder()
                .id(UUID.randomUUID().toString())
                .sku(UUID.randomUUID().toString())
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build();
        //Send command to commandGateway
        return commandGateway.send(command);
    }

}
