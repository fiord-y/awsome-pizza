package com.fiordy.pizza.producer;


import com.fiordy.pizza.order.domain.MenuItem;
import com.fiordy.pizza.order.domain.OrderItem;
import com.fiordy.pizza.order.domain.OrderType;
import com.fiordy.pizza.order.dto.CreateOrderRequest;
import com.fiordy.pizza.order.dto.CreateOrderResult;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@Slf4j
@SpringBootApplication
public class ProducerApplication {


    public static void main(String[] args) {
		SpringApplication.run(ProducerApplication.class, args);
	}

	@Autowired
	private RestTemplate restTemplate;

	@PostConstruct
	public void sendOrders(){
		var id = 1l;
		while (true) {
			var order = new CreateOrderRequest();
			order.setItems(new ArrayList<>());
			var numberOfItems = randomToInt(7);
			if (numberOfItems == 0){
				continue;
			}
			for (int i = 0; i < numberOfItems; i++){
				// choose a random item from the menu, everytime
				var orderItem = new OrderItem();
				var item = pickItem();
				orderItem.setName(item);
				order.getItems().add(orderItem);
			}
			order.setType(pickOrderType());
			order.setId(id);
			id++;
			order.setDueAt(pickDueTime());
			//log.info("Ordered {}", order);

			var resp = restTemplate.postForEntity("http://localhost:8082/order", order, CreateOrderResult.class);
			log.info("Response:>>>>>>>>>>> " + resp.getBody());

			try {
				TimeUnit.SECONDS.sleep(randomToInt(5));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private MenuItem pickItem(){
		int randomNr = randomToInt(20);
		// live dangerously by not checking out-of-bound index
        return MenuItem.values()[randomNr];
	}

	private OrderType pickOrderType(){
		int randomNr = randomToInt(3);
		return OrderType.values()[randomNr];
	}

	private int randomToInt(int size){
		return Double.valueOf(Math.floor(size * Math.random())).intValue();
	}

	private LocalDateTime pickDueTime(){
		return LocalDateTime.now().plus(randomToInt(120), ChronoUnit.MINUTES);
	}

}
