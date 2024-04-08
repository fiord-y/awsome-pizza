package com.fiordy.pizza.order.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Placehodler annotation.
 * intercept this and block everything by default
 */
@Target(ElementType.METHOD)
public @interface Authenticated {

    User[] forUsers();
    enum User {ADMIN, USER}
}
