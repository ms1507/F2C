package com.f2c.OrderService.controller;

import com.f2c.OrderService.dto.OrderRequest;
import com.f2c.OrderService.model.Order;
import com.f2c.OrderService.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/f2c/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    Logger logger = LoggerFactory.getLogger(OrderController.class);


    @GetMapping
    public ResponseEntity<List<Order>> getOrders() {
        return ResponseEntity.ok(orderService.getOrders());
    }

    @GetMapping("order/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @PostMapping("/order")
    public ResponseEntity<OrderRequest> placeOrder(@RequestBody OrderRequest orderRequest) {
        logger.info("Received Place Order request: {}", orderRequest);
        orderService.placeOrder(orderRequest, orderRequest.getProducts());
        return ResponseEntity.status(HttpStatus.CREATED).body(orderRequest);
    }

    @PutMapping("/order/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody OrderRequest orderRequest) {
        logger.info("Received update order request: {}", orderRequest);
        return ResponseEntity.ok(orderService.updateOrder(id, orderRequest));
    }

    @DeleteMapping("/order/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}