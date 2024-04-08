package com.fiordy.pizza.order.dto;

import com.fiordy.pizza.order.domain.OrderItem;
import com.fiordy.pizza.order.domain.OrderType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class CreateOrderRequest implements Serializable {
    private Long id;

    @NotNull(message = "'type' field must not be null.")
    private OrderType type;

    @NotNull(message = "'userId' needs to be provided")
    private String userId;

    @NotEmpty(message = "the order must contain at least an item")
    private List<OrderItem> items;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dueAt;
}
