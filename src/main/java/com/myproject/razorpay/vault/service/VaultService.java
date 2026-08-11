package com.myproject.razorpay.vault.service;

import com.myproject.razorpay.common.entity.Money;
import com.myproject.razorpay.payment.processor.dto.PaymentProcessorResponse;
import com.myproject.razorpay.vault.dto.request.TokenizeRequest;
import com.myproject.razorpay.vault.dto.response.TokenizeResponse;

import java.util.Map;
import java.util.UUID;

public interface VaultService {
    TokenizeResponse tokenize(TokenizeRequest request, UUID merchantId);

    PaymentProcessorResponse charge(UUID paymentId,String token, Money amount, Map<String, Object> methodDetails);
}
