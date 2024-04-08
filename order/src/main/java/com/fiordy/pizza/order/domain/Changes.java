package com.fiordy.pizza.order.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Changes implements Serializable {
    private List<String> addedIngredients;
}
