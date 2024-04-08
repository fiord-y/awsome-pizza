package com.fiordy.pizza.order.service.processor;

public interface Processor<T> {

    void process(T t);
}
