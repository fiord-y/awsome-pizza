package com.fiordy.pizza.order.service;

import com.fiordy.pizza.order.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class WorkerService {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository repository;

    private static final int BATCH_SIZE = 10;
    private static final Duration TIME_WINDOW_MIN = Duration.ofMinutes(5);

    /**

    @Scheduled(cron = "0 * * * * ?")
    void markInProgress() {
        var timeWindowStart = LocalDateTime.now().minus(TIME_WINDOW_MIN);
        // find orders in ACCEPTED and put them as IN_PROGRESS
        orderService.findNextOrders(timeWindowStart, Order.Status.ACCEPTED, BATCH_SIZE)
                .map(order -> {
                    order.setStatus(Order.Status.IN_PROGRESS);
                    return order;
                }).collectList()
                .doOnNext(orders -> repository.saveAll(orders))
                .subscribe();
    }

    @Scheduled(cron = "30 * * * * ?")
    void markDone() {
        var timeWindowStart = LocalDateTime.now().minus(TIME_WINDOW_MIN);
        // find orders in ACCEPTED and put them as IN_PROGRESS
        orderService.findNextOrders(timeWindowStart, Order.Status.IN_PROGRESS, BATCH_SIZE)
                .map(order -> {
                    order.setStatus(Order.Status.READY);
                    return order;
                }).collectList()
                .doOnNext(orders -> repository.saveAll(orders))
                .subscribe();
    }

    **/
}
