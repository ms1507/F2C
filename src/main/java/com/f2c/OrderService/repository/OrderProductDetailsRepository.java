package com.f2c.OrderService.repository;

import com.f2c.OrderService.model.OrderProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderProductDetailsRepository extends JpaRepository<OrderProductDetails, Long> {
}