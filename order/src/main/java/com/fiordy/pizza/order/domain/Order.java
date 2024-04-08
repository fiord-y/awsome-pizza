package com.fiordy.pizza.order.domain;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Document("order")
@Data
public class Order implements Comparable<Order> {
    private String id;
    private String userId;
    private LocalDateTime createdAt;
    private String receiver;
    private OrderType type;
    private List<OrderItem> items;
    private Amount totalAmount;
    private Status status;
    public enum Status { ACCEPTED, DECLINED, IN_PROGRESS, READY, DONE}
    private Boolean paid;
    private LocalDateTime dueAt;
    private String token;


    @Override
    public int compareTo(@NonNull Order o) {
        // if the other order has no specified type, this is prioritary
        if (o.getType() == null && this.getType() != null){
            return -1;
        }
        // if of different type, compare priority
        if (this.getType() != null && this.getType() != o.getType()){
            return this.getType().priority().compareTo(o.getType().priority());
        }
        // they're pizzas so we assume they have the same prep time.
        // It would be nice to compare max(prepTime...)
        var now = LocalDateTime.now();
        if (this.getDueAt() != null){
            if (o.getDueAt() != null){
                var thisDueTime = Duration.between(now, this.dueAt);
                var otherDueTime = Duration.between(now, o.getDueAt());
                return thisDueTime.compareTo(otherDueTime);
            }
            return -1;
        }
        return 0;
    }
}
