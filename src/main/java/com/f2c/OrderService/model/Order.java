package com.f2c.OrderService.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private String description;

    @Column(nullable = false)
    private LocalDateTime orderDate;

    @Column(nullable = false)
    private double orderTotal;

    @Column(nullable = false)
    private String paymentMode;

    @Column(nullable = false)
    private String paymentType;

    @Column(nullable = false)
    private String deliveryStatus;

    @Column(nullable = false)
    private LocalDateTime deliveryDate;

    @Column(nullable = false)
    private String shippingAddress;

    @Column(nullable = false)
    private String customerId;

    public Order() {
    }

    public Order(Long orderId, String description, LocalDateTime orderDate, double orderTotal, String paymentMode, String paymentType, String deliveryStatus, LocalDateTime deliveryDate, String shippingAddress, String customerId) {
        this.orderId = orderId;
        this.description = description;
        this.orderDate = orderDate;
        this.orderTotal = orderTotal;
        this.paymentMode = paymentMode;
        this.paymentType = paymentType;
        this.deliveryStatus = deliveryStatus;
        this.deliveryDate = deliveryDate;
        this.shippingAddress = shippingAddress;
        this.customerId = customerId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public LocalDateTime getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDateTime deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
