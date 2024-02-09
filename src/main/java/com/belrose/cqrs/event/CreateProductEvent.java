package com.belrose.cqrs.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateProductEvent {
    private String id;
    private String sku;
    private String name;
    private BigDecimal price;
    private Integer quantity;
}
