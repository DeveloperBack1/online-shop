package com.schneider.onlineshop.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.schneider.onlineshop.dto.CategoryDto;
import com.schneider.onlineshop.dto.OrderDto;
import com.schneider.onlineshop.dto.UserDto;
import com.schneider.onlineshop.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;
import java.sql.Timestamp;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import static org.mockito.ArgumentMatchers.any;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private OrderService orderServiceMock;
    @Autowired
    private ObjectMapper objectMapper;

    private OrderDto testOrder;
    private OrderDto existingOrder;
    private OrderDto updatedOrder;

    @BeforeEach
    void setUp() {
        testOrder = new OrderDto();
        testOrder.setOrderId(1L);
        testOrder.setUserId(100L);
        testOrder.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        testOrder.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        testOrder.setDeliveryAddress("123 Main Street");
        testOrder.setContactPhone("123-456-7890");
        testOrder.setDeliveryMethod("Courier");


        existingOrder = new OrderDto();
        existingOrder.setOrderId(1L);
        existingOrder.setUserId(100L);
        existingOrder.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        existingOrder.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        existingOrder.setDeliveryAddress("123 Main Street");
        existingOrder.setContactPhone("123-456-7890");
        existingOrder.setDeliveryMethod("Courier");

        updatedOrder = new OrderDto();
        updatedOrder.setOrderId(1L);
        updatedOrder.setUserId(101L);
        updatedOrder.setCreatedAt(existingOrder.getCreatedAt());
        updatedOrder.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        updatedOrder.setDeliveryAddress("456 Updated Street");
        updatedOrder.setContactPhone("987-654-3210");
        updatedOrder.setDeliveryMethod("Express");


    }

    @Test
    void testGetOrderById() throws Exception {
        // Arrange
        Long orderId = 1L;
        when(orderServiceMock.getOrderById(orderId)).thenReturn(Optional.of(testOrder));

        // Act & Assert
        mockMvc.perform(get("/orders/{id}", orderId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderId").value(1L))
                .andExpect(jsonPath("$.userId").value(100L))
                .andExpect(jsonPath("$.deliveryAddress").value("123 Main Street"))
                .andExpect(jsonPath("$.contactPhone").value("123-456-7890"))
                .andExpect(jsonPath("$.deliveryMethod").value("Courier"));
    }

    @Test
    void testGetOrderById_NotFound() throws Exception {
        // Arrange
        Long orderId = 2L;
        when(orderServiceMock.getOrderById(orderId)).thenReturn(Optional.empty());

        // Act & Assert
        mockMvc.perform(get("/orders/{id}", orderId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void createOrder() throws Exception {
        when(orderServiceMock.createOrder(any(OrderDto.class))).thenReturn(new OrderDto());
        this.mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                
                                        "orderId": 1,
                                        "userId": 2,
                                        "createdAt": "2024-12-10T14:48:20.050+00:00",
                                        "updatedAt": "2024-12-10T14:48:20.050+00:00",
                                        "deliveryAddress": "Hamburgerstrasse 5",
                                        "contactPhone": "1234567",
                                        "deliveryMethod": "Delivered"
                                
                                }
                                """
                        ))
                .andDo(print())
                .andExpect(status().isCreated());
    }


    @Test
    void testUpdateOrder() throws Exception {
        // Arrange
        Long orderId = 1L;
        when(orderServiceMock.updateOrder(orderId, updatedOrder)).thenReturn(Optional.of(updatedOrder));

        // Act & Assert
        mockMvc.perform(put("/orders/{id}", orderId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedOrder)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderId").value(1L))
                .andExpect(jsonPath("$.userId").value(101L))
                .andExpect(jsonPath("$.deliveryAddress").value("456 Updated Street"))
                .andExpect(jsonPath("$.contactPhone").value("987-654-3210"))
                .andExpect(jsonPath("$.deliveryMethod").value("Express"));
    }

    @Test
    void testUpdateOrder_NotFound() throws Exception {
        // Arrange
        Long orderId = 2L;
        when(orderServiceMock.updateOrder(orderId, updatedOrder)).thenReturn(Optional.empty());

        // Act & Assert
        mockMvc.perform(put("/orders/{id}", orderId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedOrder)))
                .andExpect(status().isNotFound());
    }


}