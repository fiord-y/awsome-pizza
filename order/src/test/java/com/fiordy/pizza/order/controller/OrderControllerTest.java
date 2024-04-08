package com.fiordy.pizza.order.controller;

import com.fiordy.pizza.order.domain.Order;
import com.fiordy.pizza.order.dto.CreateOrderRequest;
import com.fiordy.pizza.order.dto.CreateOrderResult;
import com.fiordy.pizza.order.dto.OrderDto;
import com.fiordy.pizza.order.service.OrderService;
import com.fiordy.pizza.order.service.OrderServiceImpl;
import com.fiordy.pizza.order.util.TokenUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @Test
    public void testCreateOrder() throws Exception {
        // Mock the service method
        CreateOrderRequest request = new CreateOrderRequest();
        CreateOrderResult expectedResult = CreateOrderResult.builder()
                .token(TokenUtil.generateToken())
                .status(Order.Status.ACCEPTED)
                .build();
        when(orderService.createOrder(request)).thenReturn(expectedResult);

        mockMvc.perform(MockMvcRequestBuilders.post("/order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                	"type": "DINE_IN",
                                	"userId": "asdf8asdf98asd",
                                	"items": [
                                		{
                                			"name": "NAPOLITANA"
                                		}
                                	]
                                }
                                """))
                .andExpect(MockMvcResultMatchers.status().isOk());
                //.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                //.andExpect(MockMvcResultMatchers.jsonPath("$.status").value("ACCEPTED"));
    }

    @Test
    public void testGetOrder() throws Exception {
        // Mock the service method
        String orderId = "exampleId";
        OrderDto expectedResult = OrderDto.builder().id("adf78asdf9asd9f78").status(Order.Status.IN_PROGRESS).build();
        when(orderService.findOne(orderId)).thenReturn(expectedResult);

        mockMvc.perform(MockMvcRequestBuilders.get("/order/{id}", orderId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
                //.andExpect(MockMvcResultMatchers.jsonPath("$.status").value("IN_PROGRESS"));
    }
}