package com.myproject.razorpay.payment.service;

import com.myproject.razorpay.payment.dto.request.CreateOrderRequest;
import com.myproject.razorpay.payment.dto.response.OrderResponse;

import java.util.UUID;

public interface OrderService {
    OrderResponse create(UUID merchantId, CreateOrderRequest request);
}
