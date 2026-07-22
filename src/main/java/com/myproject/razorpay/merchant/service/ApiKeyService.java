package com.myproject.razorpay.merchant.service;

import com.myproject.razorpay.merchant.dto.request.CreateApiKeyRequest;
import com.myproject.razorpay.merchant.dto.response.ApiKeyCreateResponse;

import java.util.UUID;

public interface ApiKeyService {
    ApiKeyCreateResponse create(UUID merchantId, CreateApiKeyRequest request);
}
