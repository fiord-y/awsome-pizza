package com.fiordy.pizza.order.service;

import com.fiordy.pizza.order.dto.CreateOrderRequest;
import com.fiordy.pizza.order.dto.CreateOrderResult;
import com.fiordy.pizza.order.dto.OrderDto;

public interface OrderService {

    CreateOrderResult createOrder(CreateOrderRequest req);

    OrderDto findOne(String id);
}
