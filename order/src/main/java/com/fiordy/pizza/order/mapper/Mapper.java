package com.fiordy.pizza.order.mapper;

import java.util.List;
import java.util.stream.Collectors;

public interface Mapper<I, O> {
    O map(I in);

    default List<O> map(List<I> in){
        if (in == null){
            return null;
        }
        return in.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}
