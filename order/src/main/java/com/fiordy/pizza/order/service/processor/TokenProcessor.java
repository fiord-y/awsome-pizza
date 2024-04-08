package com.fiordy.pizza.order.service.processor;

import com.fiordy.pizza.order.domain.Order;
import com.fiordy.pizza.order.util.TokenUtil;

public class TokenProcessor implements Processor<Order> {
    @Override
    public void process(Order order) {
        var token = TokenUtil.generateToken();
        order.setToken(token);
    }
}
