package com.belrose.cqrs.query.api.controller;

import com.belrose.cqrs.command.api.model.Product;
import com.belrose.cqrs.query.api.query.GetAllProductQuery;
import com.belrose.cqrs.query.api.query.GetProductByIdQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductQueryController {

    private final QueryGateway queryGateway;

    public ProductQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProductQuery(){
        GetAllProductQuery getAllProductQuery = new GetAllProductQuery();
        List<Product> products = queryGateway.query(getAllProductQuery, ResponseTypes.multipleInstancesOf(Product.class)).join();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/id")
    public ResponseEntity<Product> getProductByIdQuery(@RequestHeader(value = "id") String id){
        GetProductByIdQuery getProductByIdQuery = new GetProductByIdQuery();
        getProductByIdQuery.setId(id);
       Product product = queryGateway.query(getProductByIdQuery, ResponseTypes.instanceOf(Product.class)).join();
        return ResponseEntity.ok(product);
    }

}
