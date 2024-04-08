package com.fiordy.pizza.order.controller;

import com.fiordy.pizza.order.auth.Authenticated;
import com.fiordy.pizza.order.dto.CreateOrderRequest;
import com.fiordy.pizza.order.dto.CreateOrderResult;
import com.fiordy.pizza.order.dto.OrderDto;
import com.fiordy.pizza.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Authenticated(forUsers = {Authenticated.User.USER})
    @PostMapping(value = "/order", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateOrderResult> createOrder(@RequestBody @Valid CreateOrderRequest req){
        return ResponseEntity.ok(orderService.createOrder(req));
    }

    @Authenticated(forUsers = {Authenticated.User.USER})
    @GetMapping(value = "/order/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDto> getOrder(@PathVariable String id){
        return ResponseEntity.ok(orderService.findOne(id));
    }
}
