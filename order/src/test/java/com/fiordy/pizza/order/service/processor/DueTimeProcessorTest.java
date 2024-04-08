package com.fiordy.pizza.order.service.processor;

import com.fiordy.pizza.order.domain.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

public class DueTimeProcessorTest {

    @Test
    public void testProcess_WithNullDueTime_ShouldAddDefaultDueTime() {
        Order order = Mockito.mock(Order.class);

        DueTimeProcessor processor = new DueTimeProcessor();

        processor.process(order);

        verify(order).setDueAt(Mockito.any(LocalDateTime.class));
    }
}