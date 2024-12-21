package com.f2c.OrderService.service;

import com.f2c.OrderService.configuration.ProductOrderConfigurationProperties;
import com.f2c.OrderService.dto.OrderRequest;
import com.f2c.OrderService.model.Order;
import com.f2c.OrderService.repository.OrderRepository;
import com.f2cUtility.common.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

import com.f2cUtility.common.model.Product;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    private final RestTemplate restTemplate;
    ProductOrderConfigurationProperties productOrderConfigurationProperties;


    public OrderService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public OrderService(OrderRepository orderRepository, RestTemplate restTemplate, ProductOrderConfigurationProperties productOrderConfigurationProperties) {
        this.orderRepository = orderRepository;
        this.restTemplate = restTemplate;
        this.productOrderConfigurationProperties = productOrderConfigurationProperties;
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Transactional
    public Order placeOrder(OrderRequest orderRequest, List<Long> productIds) {
        Order order = orderRequest.getOrder();
        try {
            // Call ProductService to fetch product details by IDs
            List<Product> products = restTemplate.getForObject(productOrderConfigurationProperties.getUrl() + "?productIds=" + String.join(",", productIds.stream().map(String::valueOf).collect(Collectors.toList())), List.class);

            if (products == null || products.isEmpty()) {
                throw new RuntimeException("Products not found");
            }

            // Set the products in the order
            orderRequest.setProducts(productIds);

            // Save and return the order
            return orderRepository.save(order);

        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Failed to fetch product details: " + e.getMessage());
        }
    }

    public Order updateOrder(Long orderId, Order updatedOrder) {
        return orderRepository.findById(orderId).map(existingOrder -> {
            existingOrder.setDescription(updatedOrder.getDescription());
            existingOrder.setOrderDate(updatedOrder.getOrderDate());
            existingOrder.setOrderTotal(updatedOrder.getOrderTotal());
            existingOrder.setPaymentMode(updatedOrder.getPaymentMode());
            existingOrder.setPaymentType(updatedOrder.getPaymentType());
            existingOrder.setDeliveryStatus(updatedOrder.getDeliveryStatus());
            existingOrder.setDeliveryDate(updatedOrder.getDeliveryDate());
            existingOrder.setShippingAddress(updatedOrder.getShippingAddress());
            existingOrder.setCustomerId(updatedOrder.getCustomerId());
            return orderRepository.save(existingOrder);
        }).orElseThrow(() -> new RuntimeException("Order Not found for updation"));
    }

    public void deleteOrder(Long orderId) {
        if (!orderRepository.existsById(orderId)) {
            throw new RuntimeException("Order Not found for deletion");
        } else {
            orderRepository.deleteById(orderId);
        }
    }


}
