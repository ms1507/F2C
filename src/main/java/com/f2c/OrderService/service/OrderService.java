package com.f2c.OrderService.service;

import com.f2c.OrderService.configuration.ProductOrderConfigurationProperties;
import com.f2c.OrderService.dto.OrderRequest;
import com.f2c.OrderService.model.Order;
import com.f2c.OrderService.model.OrderProductDetails;
import com.f2c.OrderService.repository.OrderProductDetailsRepository;
import com.f2c.OrderService.repository.OrderRepository;
import com.f2cUtility.common.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderProductDetailsRepository orderProductDetailsRepository;

    ProductOrderConfigurationProperties productOrderConfigurationProperties;

    @Autowired
    RestTemplate restTemplate;


    public OrderService() {
    }

    public OrderService(OrderRepository orderRepository, RestTemplate restTemplate, ProductOrderConfigurationProperties productOrderConfigurationProperties) {
        this.orderRepository = orderRepository;
        this.productOrderConfigurationProperties = productOrderConfigurationProperties;
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Transactional
    public OrderRequest placeOrder(OrderRequest orderRequest, List<Product> products) {
        /*Order order = orderRequest.getOrder();
        OrderProductDetails orderProduct = new OrderProductDetails();
        try {
            // Call ProductService to fetch product details by IDs
            *//*  List<Product> products = restTemplate.getForObject(productOrderConfigurationProperties.getUrl() + "?productIds=" + String.join(",", productIds.stream().map(String::valueOf).collect(Collectors.toList())), List.class);

            if (products == null || products.isEmpty()) {
                throw new RuntimeException("Products not found");
            }*//*

            // Save and return the order
            Order savedOrder = orderRepository.save(order);
            List<OrderProductDetails> productsToSave = new ArrayList<OrderProductDetails>();
            for (Product product : products) {
//            FIXME: Need to add discount field in Product class. Test again.
                orderProduct.setDiscount(0);
                orderProduct.setProductId(product.getProductId());
                orderProduct.setOrderId(savedOrder.getOrderId());

//            FIXME: Need to calculate discount price from above discount.
                orderProduct.setDiscountPrice(0);
                // FIXME: this field need to be added in Product Class.
//                orderProductDetails.setQuantity(product.getQuantity();
                orderProduct.setQuantity(1);
                productsToSave.add(orderProduct);
            }
            //savedOrderProducts = orderProductDetailsRepository.saveAll(orderProducts);
           List<OrderProductDetails> savedOrderProducts = orderProductDetailsRepository.saveAll(productsToSave);
            if (!savedOrderProducts.isEmpty()) {
                return orderRequest;
            }
            return null;
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Failed to fetch product details: " + e.getMessage());
        }*/


        // Create and save the Order
        Order order = new Order();
        //order.setOrderId(orderRequest.getOrder().getOrderId());
        order.setDescription(orderRequest.getOrder().getDescription());
        order.setOrderDate(orderRequest.getOrder().getOrderDate());
        order.setOrderTotal(orderRequest.getOrder().getOrderTotal());
        order.setPaymentMode(orderRequest.getOrder().getPaymentMode());
        order.setPaymentType(orderRequest.getOrder().getPaymentType());
        order.setDeliveryStatus(orderRequest.getOrder().getDeliveryStatus());
        order.setDeliveryDate(orderRequest.getOrder().getDeliveryDate());
        order.setShippingAddress(orderRequest.getOrder().getShippingAddress());
        order.setCustomerId(orderRequest.getOrder().getCustomerId());
        order = orderRepository.save(order);

        // Create and save OrderProduct entries
        for (Product requestProduct : orderRequest.getProducts()) {
            OrderProductDetails orderProduct = new OrderProductDetails();
           /* Product product = .findById(requestProduct.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));*/

            //Call ProductService to fetch product details by IDs
            Product product = restTemplate.getForObject(productOrderConfigurationProperties.getUrl() + "?productId=" + requestProduct.getProductId(), Product.class);

            orderProduct.setOrderId(order.getOrderId());
            orderProduct.setProductId(product.getProductId());
            orderProduct.setQuantity(requestProduct.getQuantity());
            // orderProduct.setDiscountPrice(requestProduct.getPrice() * requestProduct.getQuantity());
            orderProduct.setDiscountPrice(requestProduct.getPrice());
            orderProduct.setDiscount(0);

            orderProductDetailsRepository.save(orderProduct);
        }
        return orderRequest;
    }

    public Order updateOrder(Long orderId, OrderRequest orderRequest) {
        Order updatedOrder = orderRequest.getOrder();
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
