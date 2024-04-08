package com.fiordy.pizza.order.mapper;

import com.fiordy.pizza.order.domain.Order;
import com.fiordy.pizza.order.dto.CreateOrderRequest;

import java.util.Collection;

public class OrderMapper implements Mapper<CreateOrderRequest, Order> {

    @Override
    public Order map(CreateOrderRequest in) {
        var order = new Order();
        order.setItems(in.getItems());
        order.setType(in.getType());
        order.setUserId(in.getUserId());
        return order;
    }

}
