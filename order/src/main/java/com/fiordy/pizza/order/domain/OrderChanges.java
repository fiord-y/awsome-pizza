package com.fiordy.pizza.order.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class OrderChanges implements Serializable {
    private List<String> allergens;
    private List<String> removedIngredients;
    private List<String> addedIngredients;
    private String comments;
}
