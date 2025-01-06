package com.f2c.OrderService.dto;

import com.f2c.OrderService.model.Order;
import com.f2cUtility.common.model.Product;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OrderResponse {
    private Long orderId;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    private Order order;
    private List<Product> products;

    public OrderResponse() {
    }

    public OrderResponse(Long orderId, Order order, List<Product> productIds) {
        this.orderId = orderId;
        this.order = order;
        this.products = productIds;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
