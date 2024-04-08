package com.fiordy.pizza.order.service.processor;

import com.fiordy.pizza.order.domain.Order;

import java.time.LocalDateTime;

public class DueTimeProcessor implements Processor<Order> {

    private static final int DEFAULT_DUE_TIME_ADDITION = 30;
    @Override
    public void process(Order order) {
        if (order.getDueAt() == null){
            order.setDueAt(LocalDateTime.now().plusMinutes(DEFAULT_DUE_TIME_ADDITION));
        }
    }
}
