package com.fiordy.pizza.order.service.processor;

import com.fiordy.pizza.order.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;


@Slf4j
@Component
public class OrderProcessorChain implements Processor<Order> {

    private final List<Processor<Order>> processors = List.of(
            new VerificationProcessor(),
            new DueTimeProcessor(),
            new PaymentProcessor(),
            new TokenProcessor(),
            new StatusProcessor()
    );

    @Override
    public void process(Order order) {
        processors.forEach(processor -> processor.process(order));
    }
}
