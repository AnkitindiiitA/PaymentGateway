package com.myproject.razorpay.payment.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.myproject.razorpay.common.entity.Money;
import com.myproject.razorpay.common.enums.PaymentMethod;
import com.myproject.razorpay.common.enums.PaymentStatus;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PaymentResponse(
        UUID id,
        UUID orderId,
        UUID merchantId,
        Money amount,
        PaymentStatus status,
        PaymentMethod method,
        Map<String, Object> methodDetails,
        String cardLastFour,
        String cardBrand,
        String bankReference,
        String errorCode,
        String errorDescription,
        Long refundAmountPrice,
        LocalDateTime capturedAt,
        LocalDateTime createdAt
) {
}
