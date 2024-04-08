package com.fiordy.pizza.order.repo;

import com.fiordy.pizza.order.domain.Order;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface OrderRepository extends MongoRepository<Order, String> {
    /**
    @Query(value = "{ 'dueAt': { $gte: ?0 }, status: ?1, sort: {'dueAt': 1, 'type.priority': 1 }, $limit: ?2")
    Flux<Order> findNextOrders(LocalDateTime dueAtAfter, Order.Status status, int batchSize);
    **/
}
