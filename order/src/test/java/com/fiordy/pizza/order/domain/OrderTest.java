package com.fiordy.pizza.order.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void compareTo_different_priority() {
        var o1 = new Order();
        o1.setType(OrderType.DINE_IN);

        var o2 = new Order();
        o2.setType(OrderType.DELIVERY);

        var compared = o1.compareTo(o2);
        assertEquals(-1, compared);
    }

    @Test
    void compareTo_same_priority() {
        var o1 = new Order();
        o1.setType(OrderType.DINE_IN);
        o1.setDueAt(LocalDateTime.now().plusMinutes(30));

        var o2 = new Order();
        o2.setType(OrderType.DINE_IN);
        o2.setDueAt(LocalDateTime.now().plusMinutes(100));

        var compared = o1.compareTo(o2);
        assertEquals(-1, compared);
    }
}