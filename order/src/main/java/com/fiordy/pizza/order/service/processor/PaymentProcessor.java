package com.fiordy.pizza.order.service.processor;

import com.fiordy.pizza.order.domain.Order;

public class PaymentProcessor implements Processor<Order> {
    @Override
    public void process(Order order) {
        //var paymentType = order.getPaymentType
        //implement strategy pattern for payment types
    }
}
