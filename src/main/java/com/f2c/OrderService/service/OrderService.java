package com.f2c.OrderService.service;

import com.f2c.OrderService.model.Order;
import com.f2c.OrderService.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
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
