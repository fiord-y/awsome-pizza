package com.fiordy.pizza.order.exception;


public class AbominationException extends RuntimeException {

    public AbominationException(){
        super("Don't try to order from this pizzeria again.");
    }
}
