package com.fiordy.pizza.order.domain;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class OrderItem implements Serializable {

    @NotNull
    private MenuItem name;
    private Amount amount;
    private OrderChanges changes;
}
