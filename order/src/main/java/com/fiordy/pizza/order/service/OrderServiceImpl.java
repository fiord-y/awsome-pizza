package com.fiordy.pizza.order.service;

import com.fiordy.pizza.order.domain.Order;
import com.fiordy.pizza.order.dto.CreateOrderRequest;
import com.fiordy.pizza.order.dto.CreateOrderResult;
import com.fiordy.pizza.order.dto.OrderDto;
import com.fiordy.pizza.order.mapper.Mapper;
import com.fiordy.pizza.order.mapper.OrderMapper;
import com.fiordy.pizza.order.repo.OrderRepository;
import com.fiordy.pizza.order.service.processor.OrderProcessorChain;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PrepService prepService;

    @Autowired
    private OrderProcessorChain processorChain;

    private final Mapper<CreateOrderRequest, Order> MAPPER = new OrderMapper();

    @Override
    public CreateOrderResult createOrder(CreateOrderRequest req){

            //map and process
            var order = MAPPER.map(req);
            log.info("Reiceved order: {}", order);
            processorChain.process(order);

            order = orderRepository.save(order);
            //enqueue
            prepService.enqueueOrder(order);

            //response
            return CreateOrderResult.builder()
                    .orderId(order.getId())
                    .token(order.getToken())
                    .url("https://someurl" + order.getId())
                    .status(order.getStatus())
                    .build();
    }

    @Override
    public OrderDto findOne(String id) {
        return orderRepository.findById(id).map(order -> {
            var dto = OrderDto.builder()
                    .id(id)
                    .status(order.getStatus())
                    .build();
            return dto;
        }).orElse(null);
    }
}
