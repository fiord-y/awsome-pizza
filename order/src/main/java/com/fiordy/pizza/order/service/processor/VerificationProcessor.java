package com.fiordy.pizza.order.service.processor;

import com.fiordy.pizza.order.domain.Order;
import com.fiordy.pizza.order.exception.AbominationException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Set;

import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;

@Slf4j
public class VerificationProcessor implements Processor<Order> {

    private static final Set<String> FORBIDDEN_INGREDIENTS = Set.of("pineapple", "nutella");
    @Override
    public void process(Order order) {
        log.info("Verifying order {}", order.getId());
        if (isNotEmpty(order.getItems())){
            var isIllegal = order.getItems()
                    .stream()
                    .filter(item -> item.getChanges() != null && isNotEmpty(item.getChanges().getAddedIngredients()))
                    .map(i -> i.getChanges().getAddedIngredients())
                    .flatMap(List::stream)
                    .map(String::toLowerCase)
                    .peek(log::info)
                    .anyMatch(FORBIDDEN_INGREDIENTS::contains);

            if (isIllegal){
                order.setStatus(Order.Status.DECLINED);
                throw new AbominationException();
            }
        }
    }
}
