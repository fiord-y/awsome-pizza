package com.fiordy.pizza.order.dto;

import com.fiordy.pizza.order.domain.Order;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class CreateOrderResult implements Serializable {
    private Order.Status status;
    private String token;
    private String orderId;
    private String url;
}
