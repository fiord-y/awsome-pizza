package com.fiordy.pizza.order.service;

import com.fiordy.pizza.order.domain.Order;
import com.fiordy.pizza.order.repo.OrderRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.PriorityQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Component
public class PrepService {
    private final PriorityQueue<Order> orderQueue = new PriorityQueue<>();

    private ExecutorService executorService;

    @Autowired
    private OrderRepository orderRepo;


    private final int MAX_PARALLEL_WORKERS = 10;
    private final int PREP_TIME_SEC = 60;

    @PostConstruct
    public void start() {
        this.executorService = Executors.newFixedThreadPool(MAX_PARALLEL_WORKERS);
        startProcessing();
    }

    public void enqueueOrder(Order order) {
        synchronized (orderQueue) {
            orderQueue.offer(order);
            orderQueue.notify(); // Notify waiting worker threads
        }
    }

    private void startProcessing() {
        for (int i = 0; i <  MAX_PARALLEL_WORKERS; i++) {
            executorService.submit(() -> {
                while (true) {
                    Order order;
                    synchronized (orderQueue) {
                        while (orderQueue.isEmpty()) {
                            try {
                                orderQueue.wait();
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                                return;
                            }
                        }
                        order = orderQueue.poll();
                    }
                    prepareOrder(order);
                }
            });
        }
    }

    private void prepareOrder(Order order) {
        //simulate order preparation
        order.setStatus(Order.Status.IN_PROGRESS);
        orderRepo.save(order);
        try {
            Thread.sleep(PREP_TIME_SEC * 1000);
        } catch (InterruptedException e) {
            log.error(e.toString());
        }
        order.setStatus(Order.Status.READY);
        orderRepo.save(order);
    }

    public void shutdown() {
        executorService.shutdown();
    }
}
