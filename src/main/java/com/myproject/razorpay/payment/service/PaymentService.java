package com.myproject.razorpay.payment.service;

import com.myproject.razorpay.payment.dto.request.PaymentInitRequest;
import com.myproject.razorpay.payment.dto.response.PaymentResponse;

import java.util.UUID;

public interface PaymentService {
    PaymentResponse initiate(UUID merchantId, PaymentInitRequest request);
}
