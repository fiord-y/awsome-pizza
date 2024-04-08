package com.fiordy.pizza.order.service.processor;

import com.fiordy.pizza.order.domain.Order;

public class StatusProcessor implements Processor<Order> {

    @Override
    public void process(Order order) {
        order.setStatus(Order.Status.ACCEPTED);
    }
}
