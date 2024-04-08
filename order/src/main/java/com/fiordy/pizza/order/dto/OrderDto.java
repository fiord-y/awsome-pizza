package com.fiordy.pizza.order.dto;

import com.fiordy.pizza.order.domain.Order;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDto {

    private String id;
    private Order.Status status;
    //..fields omitted
}
