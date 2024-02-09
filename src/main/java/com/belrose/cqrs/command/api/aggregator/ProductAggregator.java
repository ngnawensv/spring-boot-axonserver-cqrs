package com.belrose.cqrs.command.api.aggregator;

import com.belrose.cqrs.command.api.command.CreateProductCommand;
import com.belrose.cqrs.event.CreateProductEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;

@Aggregate
public class ProductAggregator {

    @AggregateIdentifier
    private String id;
    private String sku;
    private String name;
    private BigDecimal price;
    private Integer quantity;

    public ProductAggregator() {
    }

    @CommandHandler
    public ProductAggregator(CreateProductCommand command) {
        //TODO Write the business logic here
        //Build the event from command
        CreateProductEvent event = CreateProductEvent.builder()
                .id(command.getId())
                .sku(command.getSku())
                .name(command.getName())
                .price(command.getPrice())
                .quantity(command.getQuantity())
                .build();

        //Send the event to AggregateLifecycle
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(CreateProductEvent event) {
        this.id = event.getId();
        this.sku = event.getSku();
        this.name = event.getName();
        this.price = event.getPrice();
        this.quantity = event.getQuantity();

    }
}
