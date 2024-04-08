package com.fiordy.pizza.order.domain;

public enum OrderType {
    DINE_IN(1), PICK_UP(2), DELIVERY(3);

    private final Integer priority;

    OrderType(Integer priority) {
        this.priority = priority;
    }

    public Integer priority() {
        return this.priority;
    }
}
