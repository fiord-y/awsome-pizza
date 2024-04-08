package com.fiordy.pizza.order.service;

import com.fiordy.pizza.order.domain.Order;
import com.fiordy.pizza.order.repo.OrderRepository;
import com.fiordy.pizza.order.service.PrepService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PrepServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private PrepService prepService;

    @BeforeEach
    public void setUp() {
        // Initialize
        prepService.start();
    }

    @Test
    public void testEnqueueOrder() {
        Order order = new Order();
        order.setId("order1");

        // Enqueue
        prepService.enqueueOrder(order);

        //?? verify TODO
    }

}