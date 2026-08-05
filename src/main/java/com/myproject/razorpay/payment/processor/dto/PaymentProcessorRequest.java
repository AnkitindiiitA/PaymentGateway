package com.myproject.razorpay.payment.processor.dto;

import com.myproject.razorpay.common.entity.Money;
import com.myproject.razorpay.common.enums.PaymentMethod;

import java.util.Map;

public record PaymentProcessorRequest(
        PaymentMethod method,
        Money amount,
        Map<String, Object> methodDetails
) {
}
