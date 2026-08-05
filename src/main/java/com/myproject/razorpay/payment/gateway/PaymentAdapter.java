package com.myproject.razorpay.payment.gateway;

import com.myproject.razorpay.payment.gateway.dto.PaymentRequest;
import com.myproject.razorpay.payment.gateway.dto.PaymentResult;

public interface PaymentAdapter {
    PaymentResult initiate(PaymentRequest request);
}
