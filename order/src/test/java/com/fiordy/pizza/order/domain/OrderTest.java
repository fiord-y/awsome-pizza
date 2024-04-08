package com.fiordy.pizza.order.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void compareTo() {
        var o1 = new Order();
        o1.setType(OrderType.DINE_IN);

        var o2 = new Order();
        o2.setType(OrderType.DELIVERY);

        var compared = o1.compareTo(o2);
        assertEquals(-1, compared);
    }
}