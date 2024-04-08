package com.fiordy.pizza.order.service;

import com.fiordy.pizza.order.domain.Order;
import com.fiordy.pizza.order.dto.CreateOrderRequest;
import com.fiordy.pizza.order.dto.CreateOrderResult;
import com.fiordy.pizza.order.dto.OrderDto;
import com.fiordy.pizza.order.repo.OrderRepository;
import com.fiordy.pizza.order.service.OrderServiceImpl;
import com.fiordy.pizza.order.service.processor.OrderProcessorChain;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private PrepService prepService;

    @Mock
    private OrderProcessorChain processorChain;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Test
    public void testCreateOrder() {
        // Mock data
        CreateOrderRequest request = new CreateOrderRequest();
        Order order = new Order();
        order.setId("orderId");
        order.setToken("orderToken");
        // Mock repository
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        CreateOrderResult result = orderService.createOrder(request);

        verify(orderRepository, times(1)).save(any(Order.class));

        assertEquals("orderId", result.getOrderId());
        assertEquals("orderToken", result.getToken());
    }

    @Test
    public void testFindOne() {
        // Mock data
        String orderId = "orderId";
        Order order = new Order();
        order.setId(orderId);
        order.setStatus(Order.Status.READY);
        // Mock repository
        when(orderRepository.findById(orderId)).thenReturn(java.util.Optional.of(order));

        OrderDto dto = orderService.findOne(orderId);

        verify(orderRepository, times(1)).findById(orderId);

        assertEquals(orderId, dto.getId());
        assertEquals(Order.Status.READY, dto.getStatus());
    }
}