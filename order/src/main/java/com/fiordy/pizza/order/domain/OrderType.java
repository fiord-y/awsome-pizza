package com.fiordy.pizza.order.domain;

public enum OrderType {
    DINE_IN(1), PICK_UP(2), DELIVERY(3);

    private final int priority;

    OrderType(int priority) {
        this.priority = priority;
    }

    public int priority() {
        return this.priority;
    }
}
