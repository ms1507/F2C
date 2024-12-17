package com.f2c.OrderService.dto;

import com.f2c.OrderService.model.Order;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OrderRequest {
    private Order order;
    private List<Long> productIds;

    public OrderRequest() {
    }

    public OrderRequest(Order order, List<Long> productIds) {
        this.order = order;
        this.productIds = productIds;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }
}
